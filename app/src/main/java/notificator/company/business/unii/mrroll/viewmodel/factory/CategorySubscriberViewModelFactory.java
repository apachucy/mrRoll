package notificator.company.business.unii.mrroll.viewmodel.factory;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import notificator.company.business.unii.mrroll.service.datasource.CategoryApi;
import notificator.company.business.unii.mrroll.util.converter.MasterConverter;
import notificator.company.business.unii.mrroll.viewmodel.CategorySubscriberViewModel;

public class CategorySubscriberViewModelFactory implements ViewModelProvider.Factory {
    private final CategoryApi categoryApi;
    private final MasterConverter masterConverter;

    public CategorySubscriberViewModelFactory(CategoryApi categoryApi, MasterConverter converter) {
        this.categoryApi = categoryApi;
        this.masterConverter = converter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategorySubscriberViewModel.class)) {
            return (T) new CategorySubscriberViewModel(categoryApi, masterConverter);
        }
        throw new IllegalArgumentException("Unknown ViewModel class, you should provide CategorySubscriberViewModel");
    }

}

