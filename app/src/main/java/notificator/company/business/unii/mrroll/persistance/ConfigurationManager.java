package notificator.company.business.unii.mrroll.persistance;


public interface ConfigurationManager {

    String getCloudToken();

    void setCloudToken(String token);

    boolean isFirstRun();

    void setFirstRun(boolean isFirstRun);
}
