package jmuskett.example.com.newweather;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class NewWeather extends Activity {

    public final static String EXTRA_MESSAGE = "jmuskett.example.com.newweather.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_weather);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.change_city) {
            showChangeCityInputDialog();
        }
        if (id == R.id.clear_city) {
            new CityPreference(this).clearCity();
        }
        return false;
    }

    private void showChangeCityInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        input.requestFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        });

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        builder.setView(input);
        builder.show();

    }


    public void changeCity(String city) {
     //   WeatherFragment wf = (WeatherFragment) getFragmentManager().findFragmentById(R.id.container);
      //  wf.changeCity(city);
        new CityPreference(this).setCity(city);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_weather, container, false);
            return rootView;
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ExtraWeather.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
