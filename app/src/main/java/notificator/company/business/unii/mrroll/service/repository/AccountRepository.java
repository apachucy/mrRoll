package notificator.company.business.unii.mrroll.service.repository;

import android.arch.lifecycle.MutableLiveData;

import notificator.company.business.unii.mrroll.service.model.CreateUserRequest;
import notificator.company.business.unii.mrroll.service.model.CreateUserResponseWithCode;
import notificator.company.business.unii.mrroll.service.model.UpdateUserRequest;


public interface AccountRepository extends Repository {

    MutableLiveData<CreateUserResponseWithCode> createUser(CreateUserRequest userRequest);

    MutableLiveData<Integer> unregister(int userId);

    MutableLiveData<Integer> updateUser(int userId, UpdateUserRequest updateRequest);
}
