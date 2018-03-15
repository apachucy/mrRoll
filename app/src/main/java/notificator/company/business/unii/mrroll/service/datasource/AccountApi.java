package notificator.company.business.unii.mrroll.service.datasource;


import android.arch.lifecycle.LiveData;

import notificator.company.business.unii.mrroll.service.model.RegisterRequest;
import notificator.company.business.unii.mrroll.service.model.RegisterResponse;
import notificator.company.business.unii.mrroll.service.model.UpdateUserRequest;
import notificator.company.business.unii.mrroll.util.call.ApiResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountApi {

    @POST("account")
    LiveData<ApiResponse<RegisterResponse>> createUser(@Body RegisterRequest userRequest);

    @DELETE("account/{user_id}")
    LiveData<ApiResponse<Void>> unregister(@Path("user_id") int userId);

    @POST("account/{user_id}")
    LiveData<ApiResponse<Void>> updateUser(@Path("user_id") int userId, @Body UpdateUserRequest updateRequest);

}
