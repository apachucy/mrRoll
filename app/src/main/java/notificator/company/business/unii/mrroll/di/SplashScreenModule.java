package notificator.company.business.unii.mrroll.di;

import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.viewmodel.factory.SplashScreenViewModelFactory;

@Module
public class SplashScreenModule {
    @Provides
    SplashScreenViewModelFactory provideLobbyViewModelFactory(ConfigurationManager configurationManager) {
        return new SplashScreenViewModelFactory(configurationManager);
    }
}
