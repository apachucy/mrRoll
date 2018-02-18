package notificator.company.business.unii.mrroll.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.repository.AccountRepository;
import notificator.company.business.unii.mrroll.viewmodel.factory.RegisterViewModelFactory;

@Module
public class RegisterViewModule {
    @Provides
    RegisterViewModelFactory provideRegisterViewModelFactory(Context context, ConfigurationManager configurationManager, AccountRepository accountRepository) {
        return new RegisterViewModelFactory(context, configurationManager, accountRepository);
    }
}