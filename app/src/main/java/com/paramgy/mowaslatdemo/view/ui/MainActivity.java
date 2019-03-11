package com.paramgy.mowaslatdemo.view.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.paramgy.mowaslatdemo.R;
import com.paramgy.mowaslatdemo.data.model.Location;
import com.paramgy.mowaslatdemo.view_model.AppViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        Observer<List<Location>> {

    AppViewModel appViewModel;

    //Views
    Spinner spinner_current_location;
    Spinner spinner_destination;
    Button button_get_result;
    RadioButton car_button;
    RadioButton train_button;
    RadioButton tram_button;
    RadioGroup radioGroup;

    //Fields
    private ArrayAdapter spinnerAdapter;
    private String currentLocation;
    private String destination;
    private int method;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get ViewModel instance
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        //Add an observer object ( this ) to observe the LiveData object: locations list
        appViewModel.getAllLocations().observe(this, this);

        //Initializing Views On Create
        spinner_current_location = findViewById(R.id.spinner_current_location);
        spinner_destination = findViewById(R.id.spinner_destination);
        button_get_result = findViewById(R.id.button_result);
        radioGroup = findViewById(R.id.radioGroup);
        car_button = findViewById(R.id.car);
        train_button = findViewById(R.id.train);
        tram_button = findViewById(R.id.tram);

        //Set the Car button to be checked by default
        car_button.setChecked(true);

        //Adapters For Spinners
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_current_location.setAdapter(spinnerAdapter);
        spinner_destination.setAdapter(spinnerAdapter);

        //Set Spinner Listeners
        spinner_current_location.setOnItemSelectedListener(this);
        spinner_destination.setOnItemSelectedListener(this);

        //Set Button Listener
        button_get_result.setOnClickListener(this);

        //Set RadioGroup Listener
        radioGroup.setOnCheckedChangeListener(this);

    }// end onCreate();

    @Override
    public void onClick(View v) {
        Log.i("test", "button clicked!");

        //WRONG MVX PRACTISE THE VIEW SHOULDN'T TELL THE VIEWMODEL WHAT TO DO
        appViewModel.getResult(currentLocation, destination, method);
    } // end On Click

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String parentId = parent.toString().substring(103, parent.toString().length() - 1);

        if (parentId.equals("spinner_current_location")) {
            Log.i("test chosen from", "spinner_current_location");
            currentLocation = parent.getItemAtPosition(position).toString();
        } else {
            Log.i("test chosen from", "spinner_destination");
            destination = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.car:
                method = 0;
                break;
            case R.id.train:
                method = 1;
                break;
            case R.id.tram:
                method = 2;
                break;
            default:
                method = 0;
                break;
        }
    }

    //For Live Data (Observer)
    @Override
    public void onChanged(List<Location> locations) {
        //To fills the spinners with list of locations
        spinnerAdapter.addAll(locations);
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}// end MainActivity
