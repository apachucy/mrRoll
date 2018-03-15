package notificator.company.business.unii.mrroll.di;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import notificator.company.business.unii.mrroll.BuildConfig;
import notificator.company.business.unii.mrroll.R;
import notificator.company.business.unii.mrroll.service.datasource.AccountApi;
import notificator.company.business.unii.mrroll.service.datasource.CategoryApi;
import notificator.company.business.unii.mrroll.service.datasource.ControlApi;
import notificator.company.business.unii.mrroll.util.call.LiveDataCallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient providesHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG
                        ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE))
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient, Gson gson, Context applicationContext) {
        return new Retrofit.Builder().baseUrl(applicationContext.getString(R.string.base_url) + applicationContext.getString(R.string.api_version))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
    }


    @Provides
    @Singleton
    AccountApi provideAccountApi(Retrofit retrofit) {
        return retrofit.create(AccountApi.class);
    }

    @Provides
    @Singleton
    CategoryApi provideCategoryApi(Retrofit retrofit) {
        return retrofit.create(CategoryApi.class);
    }

    @Provides
    @Singleton
    ControlApi provideControlApi(Retrofit retrofit) {
        return retrofit.create(ControlApi.class);
    }

}