package jmuskett.example.com.newweather;

import android.content.Context;
import android.test.ActivityUnitTestCase;

public class CityPreferenceTests extends ActivityUnitTestCase<newWeather> {

    private CityPreference mCityPreference;

    CityPreferenceTests() {
        super(newWeather.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity().getPreferences(Context.MODE_PRIVATE).edit().clear();
        mCityPreference = new CityPreference(getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        getActivity().getPreferences(Context.MODE_PRIVATE).edit().clear();
    }
}
