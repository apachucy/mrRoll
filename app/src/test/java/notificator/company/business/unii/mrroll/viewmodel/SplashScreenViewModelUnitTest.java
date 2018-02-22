package notificator.company.business.unii.mrroll.viewmodel;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SplashScreenViewModelUnitTest {

    SplashScreenViewModel objectUnderTests;

    @Mock
    AppCompatActivity activity;
    @Mock
    ConfigurationManager configurationManager;


    @Test
    public void testOpenRegisterActivity() {


        //given
        when(configurationManager.isRequestPermissionGranted()).thenReturn(false);

        objectUnderTests = new SplashScreenViewModel(configurationManager);
        //when
        objectUnderTests.openNextActivity(activity);
        //then
        verify(configurationManager).isRequestPermissionGranted();
        verify(activity).startActivity(any(Intent.class));
        verify(activity).finish();
    }

    @Test
    public void testOpenNotificationCenterActivityActivity() {
        //given
        when(configurationManager.isRequestPermissionGranted()).thenReturn(true);

        objectUnderTests = new SplashScreenViewModel(configurationManager);
        //when
        objectUnderTests.openNextActivity(activity);
        //then
        verify(configurationManager).isRequestPermissionGranted();
        verify(activity).startActivity(any(Intent.class));
        verify(activity).finish();
    }
}