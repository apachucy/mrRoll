package notificator.company.business.unii.mrroll.persistance;


public class LocalConfigurationManager implements ConfigurationManager {

    private static final String KEY_CLOUD_TOKEN = "KEY_CLOUD_TOKEN";

    private static final String KEY_FIRST_RUN = "KEY_FIRST_RUN";


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
    public boolean isFirstRun() {
        return configurationService.getBoolean(KEY_FIRST_RUN, true);
    }

    @Override
    public void setFirstRun(boolean isFirstRun) {
        configurationService.put(KEY_FIRST_RUN, isFirstRun);
    }
}
