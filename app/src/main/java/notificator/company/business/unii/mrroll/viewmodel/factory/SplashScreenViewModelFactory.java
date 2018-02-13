package notificator.company.business.unii.mrroll.viewmodel.factory;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.viewmodel.SplashScreenViewModel;

public class SplashScreenViewModelFactory implements ViewModelProvider.Factory {
    private final ConfigurationManager configurationManager;

   public SplashScreenViewModelFactory(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashScreenViewModel.class)) {
            return (T) new SplashScreenViewModel(configurationManager);
        }
        throw new IllegalArgumentException("Unknown ViewModel class, you should provide SplashScreenViewModel");
    }
}
