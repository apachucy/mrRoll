package notificator.company.business.unii.mrroll;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import notificator.company.business.unii.mrroll.dagger.ApplicationComponent;
import notificator.company.business.unii.mrroll.dagger.ApplicationModule;
import notificator.company.business.unii.mrroll.dagger.DaggerApplicationComponent;


public class MrRollApplication extends Application {
    @NonNull
    public static MrRollApplication get(@NonNull Context context) {
        return (MrRollApplication) context.getApplicationContext();
    }

    @NonNull
    public static ApplicationComponent getApplicationComponent(@NonNull Context context) {
        return get(context).getApplicationComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


}
