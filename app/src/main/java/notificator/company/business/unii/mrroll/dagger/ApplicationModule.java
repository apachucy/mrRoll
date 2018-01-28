package notificator.company.business.unii.mrroll.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.MrRollApplication;

@Module
public class ApplicationModule implements IApplicationModule {


    /**
     * The application instance
     */
    private final MrRollApplication application;

    public ApplicationModule(MrRollApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @Override
    public Context provideApplicationContext() {
        return this.application;
    }
}
