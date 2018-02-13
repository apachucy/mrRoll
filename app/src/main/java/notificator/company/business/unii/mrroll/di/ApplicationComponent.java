package notificator.company.business.unii.mrroll.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import notificator.company.business.unii.mrroll.MrRollApplication;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.TokenInstanceService;

@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, BuilderModule.class})
@Singleton
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MrRollApplication application);

        ApplicationComponent build();
    }

 //   Context provideContext();

//    ConfigurationManager provideConfigurationManager();

 //   void inject(TokenInstanceService tokenInstanceService);

    void inject(MrRollApplication app);
}

