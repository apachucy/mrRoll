package notificator.company.business.unii.mrroll.service.datasource;


import android.arch.lifecycle.LiveData;

import java.util.List;

import notificator.company.business.unii.mrroll.service.model.Category;
import notificator.company.business.unii.mrroll.util.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi {

    @GET("categories/all")
    LiveData<ApiResponse<List<Category>>> getAllCategories();

    @GET("categories/{user_id}")
    LiveData<ApiResponse<List<Category>>> getCategories(@Path("user_id") int id);

    @POST("categories/{user_id}")
    LiveData<ApiResponse<Void>> updateCategories(@Path("user_id") int id, @Body List<Category> category);

}
