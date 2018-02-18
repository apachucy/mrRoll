package notificator.company.business.unii.mrroll.service.model;

import com.google.gson.annotations.SerializedName;


public class CreateUserResponse {
    @SerializedName("User Id")
    private final int id;

    public CreateUserResponse(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
