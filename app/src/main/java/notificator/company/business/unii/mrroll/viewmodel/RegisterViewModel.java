package notificator.company.business.unii.mrroll.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import notificator.company.business.unii.mrroll.NotificationCenterActivity;
import notificator.company.business.unii.mrroll.R;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.model.CreateUserRequest;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponse;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponseWithCode;
import notificator.company.business.unii.mrroll.service.repository.AccountRepository;

public class RegisterViewModel extends BaseViewModel {

    private ConfigurationManager configurationManager;
    private AccountRepository accountRepository;
    private MutableLiveData<Boolean> activeCheckBox;
    private String platformName;
    private Observer<CreateUserResponseWithCode> requestObserver;
    private MutableLiveData<CreateUserResponseWithCode> requestLiveData;
    private MutableLiveData<Boolean> serverRequestStatus;

    public RegisterViewModel(final Context context, final ConfigurationManager configurationManager, final AccountRepository accountRepository) {
        this.configurationManager = configurationManager;
        this.accountRepository = accountRepository;
        this.activeCheckBox = new MutableLiveData<>();
        this.platformName = context.getString(R.string.platform);
        this.activeCheckBox.setValue(configurationManager.isRequestPermissionGranted());
        this.serverRequestStatus = new MutableLiveData<>();
        this.requestObserver = new Observer<CreateUserResponseWithCode>() {
            @Override
            public void onChanged(@Nullable CreateUserResponseWithCode createUserResponseWithCode) {
                if (createUserResponseWithCode.getCreateUserResponse() != null && createUserResponseWithCode.getStatusCode() == 200) {
                    onRegistrationSuccess(createUserResponseWithCode.getCreateUserResponse());
                    return;
                }
                onRegistrationFailure();

            }
        };
    }

    @Override
    public void openNextActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, NotificationCenterActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * This should react on
     *
     * @return
     */
    public MutableLiveData<Boolean> getActiveCheckBox() {
        return activeCheckBox;
    }

    public void changeCheckboxState(boolean isSelected) {
        activeCheckBox.setValue(isSelected);
    }

    public void onSaveButtonPressed() {
        String token = configurationManager.getCloudToken();
        CreateUserRequest user = new CreateUserRequest(platformName, token);
        requestLiveData = accountRepository.createUser(user);
        requestLiveData.observeForever(requestObserver);
    }

    private void onRegistrationSuccess(CreateUserResponse createUserResponse) {
        configurationManager.setRequestPermissionGranted(true);
        configurationManager.setUserId(createUserResponse.getId());
        serverRequestStatus.setValue(true);
    }

    private void onRegistrationFailure() {
        serverRequestStatus.setValue(false);
    }

    public MutableLiveData<Boolean> getRequestStatus() {
        return serverRequestStatus;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        requestLiveData.removeObserver(requestObserver);
    }
}