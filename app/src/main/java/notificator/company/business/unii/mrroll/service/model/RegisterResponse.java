package notificator.company.business.unii.mrroll.service.model;

import com.google.gson.annotations.SerializedName;


public class RegisterResponse {
    @SerializedName("user_id")
    private final int id;

    public RegisterResponse(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
