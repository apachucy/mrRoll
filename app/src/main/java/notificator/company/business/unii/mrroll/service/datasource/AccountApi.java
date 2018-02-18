package notificator.company.business.unii.mrroll.service.datasource;


import notificator.company.business.unii.mrroll.service.model.CreateUserRequest;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponse;
import notificator.company.business.unii.mrroll.service.model.UpdateUserRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountApi {

    @POST("account")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest userRequest);

    @DELETE("account/{user_id}")
    Call<Void> unregister(@Path("user_id") int userId);

    @POST("account/{user_id}")
    Call<Void> updateUser(@Path("user_id") int userId, @Body UpdateUserRequest updateRequest);

}
