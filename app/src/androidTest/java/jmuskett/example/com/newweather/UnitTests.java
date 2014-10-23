package jmuskett.example.com.newweather;

import android.test.AndroidTestCase;
import android.content.Context;



import org.json.JSONException;
import org.json.JSONObject;




public class UnitTests extends AndroidTestCase {
    private Context mTargetContext;



    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mTargetContext = getContext();

    }

    public void test_json_data_fetch() throws JSONException {
        JSONObject data =  RemoteFetch.getJSON(mTargetContext, "sale");

        assertEquals(data.getString("name"),"Sale");
    }

}
