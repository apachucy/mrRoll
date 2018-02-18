package notificator.company.business.unii.mrroll.service.repository;


import android.arch.lifecycle.MutableLiveData;

import notificator.company.business.unii.mrroll.service.datasource.AccountApi;
import notificator.company.business.unii.mrroll.service.model.CreateUserRequest;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponse;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponseWithCode;
import notificator.company.business.unii.mrroll.service.model.UpdateUserRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AccountRepositoryImpl implements AccountRepository {
    private final AccountApi accountApi;

    public AccountRepositoryImpl(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    @Override
    public MutableLiveData<CreateUserResponseWithCode> createUser(CreateUserRequest userRequest) {
        final MutableLiveData<CreateUserResponseWithCode> liveData = new MutableLiveData<>();
        Call<CreateUserResponse> createUserResponseCall = accountApi.createUser(userRequest);
        createUserResponseCall.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                CreateUserResponseWithCode createUserResponseWithCode = new CreateUserResponseWithCode(response.body(), response.code());
                liveData.setValue(createUserResponseWithCode);
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                int statusCode = STATUS_CODE_UNKNOWN;
                if (t instanceof HttpException) {
                    HttpException exception = ((HttpException) t);
                    statusCode = exception.code();
                }
                CreateUserResponseWithCode createUserResponseWithCode = new CreateUserResponseWithCode(null, statusCode);
                liveData.setValue(createUserResponseWithCode);

            }

        });

        return liveData;
    }

    @Override
    public MutableLiveData<Integer> unregister(int userId) {
        final MutableLiveData<Integer> liveData = new MutableLiveData<>();
        Call<Void> unregisterCall = accountApi.unregister(userId);
        unregisterCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                liveData.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int statusCode = STATUS_CODE_UNKNOWN;
                if (t instanceof HttpException) {
                    HttpException exception = ((HttpException) t);
                    statusCode = exception.code();
                }
                liveData.setValue(statusCode);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<Integer> updateUser(int userId, UpdateUserRequest updateRequest) {
        final MutableLiveData<Integer> liveData = new MutableLiveData<>();
        Call<Void> updateUserCall = accountApi.updateUser(userId, updateRequest);
        updateUserCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                liveData.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int statusCode = STATUS_CODE_UNKNOWN;
                if (t instanceof HttpException) {
                    HttpException exception = ((HttpException) t);
                    statusCode = exception.code();
                }
                liveData.setValue(statusCode);
            }
        });

        return liveData;
    }
}
