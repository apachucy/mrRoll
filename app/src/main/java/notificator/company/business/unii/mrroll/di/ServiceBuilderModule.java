package notificator.company.business.unii.mrroll.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import notificator.company.business.unii.mrroll.service.TokenInstanceService;

@Module
public abstract class ServiceBuilderModule {
    @ContributesAndroidInjector
    abstract TokenInstanceService bindTokenInstanceService();

}
