package notificator.company.business.unii.mrroll;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import notificator.company.business.unii.mrroll.viewmodel.SplashScreenViewModel;
import notificator.company.business.unii.mrroll.viewmodel.factory.SplashScreenViewModelFactory;

public class SplashScreenActivity extends AppCompatActivity {
    @Inject
    SplashScreenViewModelFactory viewModelFactory;
    private SplashScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel.class);
        viewModel.openNextActivity(this);
    }
}
