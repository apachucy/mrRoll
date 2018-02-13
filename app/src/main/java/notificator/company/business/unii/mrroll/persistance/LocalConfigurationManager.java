package notificator.company.business.unii.mrroll.persistance;


public class LocalConfigurationManager implements ConfigurationManager {

    private static final String KEY_CLOUD_TOKEN = "KEY_CLOUD_TOKEN";
    private static final String NOTIFICATION_PERMISSION_GRANTED = "NOTIFICATION_PERMISSION_GRANTED";

    private ConfigurationService configurationService;

    public LocalConfigurationManager(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public String getCloudToken() {
        return configurationService.getString(KEY_CLOUD_TOKEN, "");
    }

    @Override
    public void setCloudToken(String token) {
        configurationService.put(KEY_CLOUD_TOKEN, token);
    }

    @Override
    public boolean isRequestPermissionGranted() {
        return configurationService.getBoolean(NOTIFICATION_PERMISSION_GRANTED, false);
    }

    @Override
    public void setRequestPermissionGranted(boolean requestPermissionGranted) {
        configurationService.put(NOTIFICATION_PERMISSION_GRANTED, requestPermissionGranted);
    }


}
