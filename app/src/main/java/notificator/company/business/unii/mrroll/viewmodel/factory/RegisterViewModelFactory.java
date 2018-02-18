package notificator.company.business.unii.mrroll.viewmodel.factory;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.repository.AccountRepository;
import notificator.company.business.unii.mrroll.viewmodel.RegisterViewModel;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    private final ConfigurationManager configurationManager;
    private final AccountRepository accountRepository;
    private final Context context;

    public RegisterViewModelFactory(final Context context, final ConfigurationManager configurationManager, final AccountRepository accountRepository) {
        this.configurationManager = configurationManager;
        this.accountRepository = accountRepository;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(context, configurationManager, accountRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class, you should provide RegisterViewModel");
    }

}
