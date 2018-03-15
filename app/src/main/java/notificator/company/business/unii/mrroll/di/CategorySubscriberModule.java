package notificator.company.business.unii.mrroll.di;


import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.service.datasource.CategoryApi;
import notificator.company.business.unii.mrroll.util.converter.MasterConverter;
import notificator.company.business.unii.mrroll.viewmodel.factory.CategorySubscriberViewModelFactory;

@Module
public class CategorySubscriberModule {

    @Provides
    CategorySubscriberViewModelFactory provideCategorySubscriberViewModelFactory(CategoryApi categoryApi, MasterConverter converter) {
        return new CategorySubscriberViewModelFactory(categoryApi, converter);
    }

}
