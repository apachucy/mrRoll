package notificator.company.business.unii.mrroll;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import notificator.company.business.unii.mrroll.viewmodel.RegisterViewModel;
import notificator.company.business.unii.mrroll.viewmodel.factory.RegisterViewModelFactory;

public class RegisterActivity extends AppCompatActivity {
    private static final int PROGRESS_BUTTON_STATE_IDLE = 0;
    private static final int PROGRESS_BUTTON_STATE_LOADING = 50;

    @BindView(R.id.button_terms_condition)
    com.dd.CircularProgressButton acceptTermsAndConditionButton;

    @Inject
    RegisterViewModelFactory viewModelFactory;
    private RegisterViewModel viewModel;

    private final Observer<Boolean> requestStatusObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            acceptTermsAndConditionButton.setIndeterminateProgressMode(false);
            acceptTermsAndConditionButton.setProgress(PROGRESS_BUTTON_STATE_IDLE);
            if (aBoolean) {
                //success
                viewModel.openNextActivity(RegisterActivity.this);
            } else {
                Snackbar.make(acceptTermsAndConditionButton, getString(R.string.network_error), Snackbar.LENGTH_SHORT).show();
            }

            //failure
        }
    };
    private final Observer<Boolean> checkBoxStatusObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            acceptTermsAndConditionButton.setEnabled(aBoolean);
        }
    };

    @OnCheckedChanged(R.id.check_box_terms_condition)
    void onTermsCheckboxStateChanged(boolean state) {
        viewModel.changeCheckboxState(state);
    }

    @OnClick(R.id.button_terms_condition)
    public void onAcceptTermsAndConditionButtonClicked() {
        acceptTermsAndConditionButton.setIndeterminateProgressMode(true);
        acceptTermsAndConditionButton.setProgress(PROGRESS_BUTTON_STATE_LOADING);
        viewModel.onSaveButtonPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);

        viewModel.getActiveCheckBox().observe(this, checkBoxStatusObserver);
        viewModel.getRequestStatus().observe(this, requestStatusObserver);
    }
}