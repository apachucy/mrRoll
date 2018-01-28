package notificator.company.business.unii.mrroll.persistance;


import android.content.Context;
import android.content.SharedPreferences;

public class ConfigurationService {

    private final Context context;

    final SharedPreferences preferences;


    public ConfigurationService(Context context, String fileName) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public boolean contains(String key) {
        synchronized (preferences) {
            return preferences.contains(key);
        }
    }

    public void remove(String key) {
        synchronized (preferences) {
            preferences.edit().remove(key).apply();
        }
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    public String getString(String key, String defaultValue) {
        synchronized (preferences) {
            return preferences.getString(key, defaultValue);
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        synchronized (preferences) {
            return preferences.getBoolean(key, defaultValue);
        }
    }

    public void put(final String key, final String value) {
        synchronized (preferences) {
            preferences.edit().putString(key, value).apply();
        }
    }

    public void put(final String key, final boolean value) {
        synchronized (preferences) {
            preferences.edit().putBoolean(key, value).apply();
        }
    }

}
