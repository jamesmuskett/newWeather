package jmuskett.example.com.newweather;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by jmuskett on 02/10/2014.
 */
public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCity() {
        return prefs.getString("city", "Manchester, gb");
    }

    void clearCity() {
        prefs.edit().remove("city").commit();
    }

    void setCity(String city) {
        prefs.edit().putString("city", city).commit();
    }
}
