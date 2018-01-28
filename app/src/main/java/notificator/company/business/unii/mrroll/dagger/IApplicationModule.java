package notificator.company.business.unii.mrroll.dagger;


import android.content.Context;

import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;

public interface IApplicationModule {

    Context provideApplicationContext();

    ConfigurationManager provideConfigurationManager(Context context);
}

