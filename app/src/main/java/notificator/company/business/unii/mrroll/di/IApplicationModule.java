package notificator.company.business.unii.mrroll.di;


import android.content.Context;

import notificator.company.business.unii.mrroll.MrRollApplication;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.util.converter.MasterConverter;
import notificator.company.business.unii.mrroll.util.converter.SubConverter;

public interface IApplicationModule {

    Context provideApplicationContext(MrRollApplication application);

    ConfigurationManager provideConfigurationManager(Context context);

    MasterConverter provideMasterConverter(SubConverter converter);

    SubConverter provideSubConverter();
}

