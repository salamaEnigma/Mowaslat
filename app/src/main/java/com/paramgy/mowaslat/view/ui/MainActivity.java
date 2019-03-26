package com.paramgy.mowaslat.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.view_model.AppViewModel;
import com.paramgy.mowaslat.view_model.AppViewModelInterface;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    AppViewModelInterface appViewModelInterface;

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

    //Navigation Drawer Fields
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get ViewModel instance
        appViewModelInterface = ViewModelProviders.of(this).get(AppViewModel.class);
        //Add an observer object ( this ) to observe the LiveData object: locations list
        appViewModelInterface.getAllLocations().observe(this, this);

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
        spinnerAdapter = new ArrayAdapter(this, R.layout.my_simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.my_simple_spinner_item);
        spinner_current_location.setAdapter(spinnerAdapter);
        spinner_destination.setAdapter(spinnerAdapter);

        //Set Spinner Listeners
        spinner_current_location.setOnItemSelectedListener(this);
        spinner_destination.setOnItemSelectedListener(this);

        //Set Button Listener
        button_get_result.setOnClickListener(this);

        //Set RadioGroup Listener
        radioGroup.setOnCheckedChangeListener(this);

        //Navigation Drawer Settings
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }// end onCreate();


    public void menuButtonClicked(View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //
    @Override
    public void onClick(View v) {
        Log.i("test", "result button clicked!");
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("currentLocation", currentLocation);
        resultIntent.putExtra("destination", destination);
        resultIntent.putExtra("method", method);
        startActivity(resultIntent);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.message:
                //Do Something
                startActivity(new Intent(this, MessageActivity.class));

                break;
            case R.id.contact:
                //Do Something
                startActivity(new Intent(this, ContactActivity.class));

                break;
            case R.id.share:
                //Do Something
                Toast.makeText(this, "Share Action", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                //Do Something
                Toast.makeText(this, "Rate Action", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}// end MainActivity
