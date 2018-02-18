package notificator.company.business.unii.mrroll.service.datasource;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ControlApi {

    @GET("ping")
    Call<Void> ping();
}
