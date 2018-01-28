package notificator.company.business.unii.mrroll.dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;

@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {

    Context provideContext();

    ConfigurationManager provideConfigurationManager();
}

