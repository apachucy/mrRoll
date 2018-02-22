package notificator.company.business.unii.mrroll.di;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import notificator.company.business.unii.mrroll.MrRollApplication;

@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ActivityBuilderModule.class, ServiceBuilderModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MrRollApplication application);

        ApplicationComponent build();
    }

    //   Context provideContext();

    //ConfigurationManager provideConfigurationManager();

    //   void inject(TokenInstanceService tokenInstanceService);

    void inject(MrRollApplication app);
}

