package notificator.company.business.unii.mrroll.service;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import notificator.company.business.unii.mrroll.MrRollApplication;
import notificator.company.business.unii.mrroll.dagger.ApplicationComponent;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;


public class TokenInstanceService extends FirebaseInstanceIdService {

    private static final String TAG = TokenInstanceService.class.toString();
    @Inject
    ConfigurationManager configurationManager;

    public TokenInstanceService() {
        injectDependencies(((MrRollApplication) getApplication()).getApplicationComponent());
    }


    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String previousToken = configurationManager.getCloudToken();

        Log.v(TAG, "Obtained refreshed Token: " + refreshedToken);
        configurationManager.setCloudToken(refreshedToken);

        //TODO: send message to server side
    }


    protected void injectDependencies(@NonNull ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}
