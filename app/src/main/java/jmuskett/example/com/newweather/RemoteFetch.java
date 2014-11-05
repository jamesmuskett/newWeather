package jmuskett.example.com.newweather;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jmuskett on 01/10/2014.
 */
public class RemoteFetch {

    private static final String WEATHER_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";


    public static void makeJSONRequest(final Context context, final String city, final JSONResponseListener listener) {
        final Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(context, city);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json == null) {
                            listener.onJSONDataFailure();
                        } else {
                            listener.onJSONDataReceived(json);
                        }
                    }
                });
            }
        }.start();
    }

    private static JSONObject getJSON(Context context, String city) {
        try {
            URL url = new URL(String.format(WEATHER_API, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", context.getString(R.string.weather_api_key));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                json.append(tmp).append("\n");
            }
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                Log.d("newWeather", "cod wasn't 200! It was " + data.getInt("cod"));
                return null;
            }

            return data;

        } catch (IOException e) {
            Log.e("newWeather", e.getMessage(), e);
            return null;
        } catch (JSONException e) {
            Log.e("newWeather", e.getMessage(), e);
            return null;
        }
    }

    public interface JSONResponseListener {
        void onJSONDataReceived(JSONObject object);

        void onJSONDataFailure();
    }
}
