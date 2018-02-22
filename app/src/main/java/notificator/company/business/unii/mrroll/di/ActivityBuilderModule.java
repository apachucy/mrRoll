package notificator.company.business.unii.mrroll.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import notificator.company.business.unii.mrroll.RegisterActivity;
import notificator.company.business.unii.mrroll.SplashScreenActivity;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = SplashScreenModule.class)
    abstract SplashScreenActivity bindSplashScreenActivity();

    @ContributesAndroidInjector(modules = RegisterViewModule.class)
    abstract RegisterActivity bindRegisterActivity();
}
