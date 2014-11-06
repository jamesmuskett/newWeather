package jmuskett.example.com.newweather;

/**
 * Created by jmuskett on 05/11/2014.
 */
public class City {
    String name;

    City(String cityName) {
        name = cityName;
    }

    void setName(String cityName) {
        name = cityName;
    }

    String getName() {
        return name;
    }
}
