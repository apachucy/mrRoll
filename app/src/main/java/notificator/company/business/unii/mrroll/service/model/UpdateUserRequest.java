package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {
    @SerializedName("Old Token")
    private final String oldToken;
    @SerializedName("New Token")
    private final String newToken;

    public UpdateUserRequest(String oldToken, String newToken) {
        this.oldToken = oldToken;
        this.newToken = newToken;
    }

    public String getOldToken() {
        return oldToken;
    }

    public String getNewToken() {
        return newToken;
    }
}
