package notificator.company.business.unii.mrroll.service.datasource;


import notificator.company.business.unii.mrroll.service.model.Category;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi {

    @GET("categories/all")
    Call<Category> getAllCategories();

    @GET("categories/{user_id}")
    Call<Category> getCategories(@Path("user_id") int id);

    @POST("categories/{user_id}")
    Call<Void> updateCategories(@Path("user_id") int id, @Body Category category);

}
