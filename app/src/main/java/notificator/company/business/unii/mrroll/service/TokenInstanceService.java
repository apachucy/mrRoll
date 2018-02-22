package notificator.company.business.unii.mrroll.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;


public class TokenInstanceService extends FirebaseInstanceIdService {

    private static final String TAG = TokenInstanceService.class.toString();
    @Inject
    ConfigurationManager configurationManager;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        String previousToken = configurationManager.getCloudToken();
        Log.v(TAG, "Previous token: " + previousToken);

        Log.v(TAG, "Obtained refreshed Token: " + refreshedToken);
        configurationManager.setCloudToken(refreshedToken);

        //TODO: send message to server side
    }

}
