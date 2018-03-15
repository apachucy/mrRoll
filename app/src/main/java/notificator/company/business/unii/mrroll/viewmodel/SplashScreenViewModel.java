package notificator.company.business.unii.mrroll.viewmodel;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import notificator.company.business.unii.mrroll.CategorySubscriberActivity;
import notificator.company.business.unii.mrroll.RegisterActivity;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;

public class SplashScreenViewModel extends BaseViewModel {
    private final ConfigurationManager configurationManager;

    public SplashScreenViewModel(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @Override
    public void openNextActivity(AppCompatActivity activity) {
        Intent intent = null;
        if (isRequestPermissionGranted()) {
            intent = new Intent(activity, CategorySubscriberActivity.class);
        } else {
            intent = new Intent(activity, RegisterActivity.class);
        }
        activity.startActivity(intent);
        activity.finish();
    }


    private boolean isRequestPermissionGranted() {
        return configurationManager.isRequestPermissionGranted();
    }
}
