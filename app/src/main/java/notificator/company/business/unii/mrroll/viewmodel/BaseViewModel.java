package notificator.company.business.unii.mrroll.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseViewModel extends ViewModel {

    abstract void openNextActivity(AppCompatActivity activity);

    void openPreviousActivity(AppCompatActivity activity) {
    }

}
