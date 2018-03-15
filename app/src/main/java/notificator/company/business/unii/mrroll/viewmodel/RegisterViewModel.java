package notificator.company.business.unii.mrroll.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import notificator.company.business.unii.mrroll.NotificationCenterActivity;
import notificator.company.business.unii.mrroll.R;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.datasource.AccountApi;
import notificator.company.business.unii.mrroll.service.model.RegisterRequest;
import notificator.company.business.unii.mrroll.service.model.RegisterResponse;
import notificator.company.business.unii.mrroll.util.call.ApiResponse;

public class RegisterViewModel extends BaseViewModel {

    private ConfigurationManager configurationManager;
    private AccountApi accountApi;
    private MutableLiveData<Boolean> activeCheckBox;
    private String platformName;
    private Observer<ApiResponse<RegisterResponse>> requestObserver;
    private LiveData<ApiResponse<RegisterResponse>> requestLiveData;
    private MutableLiveData<Boolean> serverRequestStatusAccepted;

    public RegisterViewModel(final Context context, final ConfigurationManager configurationManager, final AccountApi accountApi) {
        this.configurationManager = configurationManager;
        this.accountApi = accountApi;
        this.activeCheckBox = new MutableLiveData<>();
        this.platformName = context.getString(R.string.platform);
        this.activeCheckBox.setValue(configurationManager.isRequestPermissionGranted());
        this.serverRequestStatusAccepted = new MutableLiveData<>();
        this.requestObserver = new Observer<ApiResponse<RegisterResponse>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<RegisterResponse> createUserResponseWithCode) {
                if (createUserResponseWithCode.body != null && createUserResponseWithCode.isSuccessful()) {
                    onRegistrationSuccess(createUserResponseWithCode.body);
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
        RegisterRequest user = new RegisterRequest(platformName, token);
        requestLiveData = accountApi.createUser(user);

        if (!requestLiveData.hasActiveObservers()) {
            requestLiveData.observeForever(requestObserver);
        }

    }

    private void onRegistrationSuccess(RegisterResponse registerResponse) {
        configurationManager.setRequestPermissionGranted(true);
        configurationManager.setUserId(registerResponse.getId());
        serverRequestStatusAccepted.setValue(true);
    }

    private void onRegistrationFailure() {
        serverRequestStatusAccepted.setValue(false);
    }

    public MutableLiveData<Boolean> getRequestStatus() {
        return serverRequestStatusAccepted;
    }


    @Override
    protected void onCleared() {
        if (requestLiveData != null && requestLiveData.hasObservers()) {
            requestLiveData.removeObserver(requestObserver);
        }
        super.onCleared();

    }
}