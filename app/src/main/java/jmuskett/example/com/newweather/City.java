package jmuskett.example.com.newweather;

/**
 * Created by jmuskett on 05/11/2014.
 */
public class City {
    private String name;

    public City(String cityName) {
        name = cityName;
    }

    public void setName(String cityName) {
        name = cityName;
    }

    public String getName() {
        return name;
    }
}
