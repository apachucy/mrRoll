package notificator.company.business.unii.mrroll.di;


import android.content.Context;

import notificator.company.business.unii.mrroll.MrRollApplication;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;

public interface IApplicationModule {

    Context provideApplicationContext(MrRollApplication application);

    ConfigurationManager provideConfigurationManager(Context context);
}

