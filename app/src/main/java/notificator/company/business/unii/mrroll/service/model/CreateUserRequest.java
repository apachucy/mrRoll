package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

public class CreateUserRequest {
    @SerializedName("Platform Name")
    private final String platformName;
    @SerializedName("Token")
    private final String platformToken;

    public CreateUserRequest(String platformName, String platformToken) {
        this.platformName = platformName;
        this.platformToken = platformToken;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformToken() {
        return platformToken;
    }
}
