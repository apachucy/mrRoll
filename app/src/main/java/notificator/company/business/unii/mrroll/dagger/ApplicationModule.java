package notificator.company.business.unii.mrroll.dagger;

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
    private final MrRollApplication application;
    private final String CONFIG_FILE = "configuration";

    public ApplicationModule(MrRollApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @Override
    public Context provideApplicationContext() {
        return this.application;
    }

    @Override
    public ConfigurationManager provideConfigurationManager(Context context) {
        return new LocalConfigurationManager(new ConfigurationService(context, CONFIG_FILE));
    }
}
