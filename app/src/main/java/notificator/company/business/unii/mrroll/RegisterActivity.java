package notificator.company.business.unii.mrroll;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import notificator.company.business.unii.mrroll.viewmodel.RegisterViewModel;
import notificator.company.business.unii.mrroll.viewmodel.factory.RegisterViewModelFactory;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.button_terms_condition)
    CircularProgressButton acceptTermsAndConditionButton;

    @Inject
    RegisterViewModelFactory viewModelFactory;
    private RegisterViewModel viewModel;

    private final Observer<Boolean> requestStatusObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            acceptTermsAndConditionButton.revertAnimation();
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
        acceptTermsAndConditionButton.startAnimation();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        acceptTermsAndConditionButton.dispose();
    }
}