package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("os")
    private final String platformName;
    @SerializedName("token")
    private final String platformToken;

    public RegisterRequest(String platformName, String platformToken) {
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
