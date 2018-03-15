package notificator.company.business.unii.mrroll.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import notificator.company.business.unii.mrroll.persistance.ConfigurationManager;
import notificator.company.business.unii.mrroll.service.datasource.AccountApi;
import notificator.company.business.unii.mrroll.service.model.RegisterRequest;
import notificator.company.business.unii.mrroll.service.model.RegisterResponse;
import notificator.company.business.unii.mrroll.util.call.ApiResponse;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterViewModelUnitTest {
    //This rule if for LiveData to bypas AndroidMainThread
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    ConfigurationManager configurationManager;
    @Mock
    Context context;
    @Mock
    AccountApi accountApi;
    @Mock
    Observer<Boolean> observer;

    RegisterViewModel objectUnderTest;

    @Test
    public void testCheckboxSelected() {
        //given
        when(configurationManager.isRequestPermissionGranted()).thenReturn(false);
        objectUnderTest = new RegisterViewModel(context, configurationManager, accountApi);
        objectUnderTest.getActiveCheckBox().observeForever(observer);
        //when
        objectUnderTest.changeCheckboxState(true);

        //then
        assertTrue(objectUnderTest.getActiveCheckBox().getValue().equals(true));
        verify(observer).onChanged(true);

        objectUnderTest.getActiveCheckBox().removeObserver(observer);
    }

    @Test
    public void testCheckboxUnselected() {
        //given
        when(configurationManager.isRequestPermissionGranted()).thenReturn(true);
        objectUnderTest = new RegisterViewModel(context, configurationManager, accountApi);
        objectUnderTest.getActiveCheckBox().observeForever(observer);
        //when
        objectUnderTest.changeCheckboxState(false);

        //then
        assertTrue(objectUnderTest.getActiveCheckBox().getValue().equals(false));
        verify(observer).onChanged(false);

        objectUnderTest.getActiveCheckBox().removeObserver(observer);

    }

    @Test
    public void testSaveButton() {
        //given
        when(configurationManager.getCloudToken()).thenReturn("testToken");
        when(context.getString(any(Integer.class))).thenReturn("Android");

        when(accountApi.createUser(any(RegisterRequest.class))).thenReturn(new MutableLiveData<ApiResponse<RegisterResponse>>());
        objectUnderTest = new RegisterViewModel(context, configurationManager, accountApi);
        //when
        objectUnderTest.onSaveButtonPressed();
        ArgumentCaptor<RegisterRequest> argument = ArgumentCaptor.forClass(RegisterRequest.class);
        //then
        verify(configurationManager).getCloudToken();

        verify(accountApi).createUser(argument.capture());
        assertEquals("Android", argument.getValue().getPlatformName());
        assertEquals("testToken", argument.getValue().getPlatformToken());
    }


}
