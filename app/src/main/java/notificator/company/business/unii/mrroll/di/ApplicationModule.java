package notificator.company.business.unii.mrroll.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.MrRollApplication;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.persistance.ConfigurationService;
import notificator.company.business.unii.mrroll.persistance.LocalConfigurationManager;

@Module
public class ApplicationModule implements IApplicationModule {


    /**
     * The application instance
     */

    private final String CONFIG_FILE = "configuration";

    @Provides
    @Singleton
    @Override
    public Context provideApplicationContext(MrRollApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    @Override
    public ConfigurationManager provideConfigurationManager(Context context) {
        return new LocalConfigurationManager(new ConfigurationService(context, CONFIG_FILE));
    }
}
