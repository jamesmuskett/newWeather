package jmuskett.example.com.newweather;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class CityListActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        listView = (ListView) findViewById(R.id.city_list);
        City[] cityValues = new City[]{
                new City("salford"),
                new City("sale"),
                new City("oldham"),
                new City("leigh"),
                new City("bolton"),
                new City("salford"),
                new City("sale"),
                new City("oldham"),
                new City("leigh"),
                new City("bolton"),
                new City("salford"),
                new City("sale"),
                new City("oldham"),
                new City("leigh"),
                new City("bolton"),
                new City("salford"),
                new City("sale"),
                new City("oldham"),
                new City("leigh"),
                new City("bolton"),
                new City("salford"),
                new City("sale"),
                new City("oldham"),
                new City("leigh"),
                new City("bolton")
        };


        ListViewAdapter adapter = new ListViewAdapter(this, cityValues);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                City item = (City) listView.getItemAtPosition(position);
                sendMessage(item.getName());
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.city_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.change_city) {
            showChangeCityInputDialog();
        }
        if (id == R.id.set_city) {
            showSetCityInputDialog();
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

    private void showSetCityInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        input.requestFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setCity(input.getText().toString());
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        });

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        builder.setView(input);
        builder.show();

    }

    public void setCity(String city) {
        new CityPreference(this).setCity(city);
    }

    public void changeCity(String city) {
        sendMessage(city);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        sendMessage(message);
    }

    public void sendMessage(String message) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra(WeatherActivity.EXTRA_CHANGE_CITY_MESSAGE, message);
        startActivity(intent);
    }

}
