package com.paramgy.mowaslat.features.main.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.paramgy.mowaslat.data.firestore.TinyDB;
import com.paramgy.mowaslat.data.model.pojos.Location;
import com.paramgy.mowaslat.view.ContactActivity;
import com.paramgy.mowaslat.features.main.contracts.MainViewContract;
import com.paramgy.mowaslat.features.message.view.MessageActivity;
import com.paramgy.mowaslat.features.result.view.ResultActivity;
import com.paramgy.mowaslat.features.main.viewmodel.LocationsViewModel;
import com.paramgy.mowaslat.features.main.contracts.LocationsViewModelContract;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    LocationsViewModelContract locationsViewModelContract;

    //Views
    @BindView(R.id.spinner_current_location)
    Spinner spinner_current_location;
    @BindView(R.id.spinner_destination)
    Spinner spinner_destination;
    @BindView(R.id.button_result)
    Button button_get_result;
    @BindView(R.id.car)
    RadioButton car_button;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    //Navigation Drawer Fields
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    //Fields
    TinyDB tinyDB;
    private static final String TAG = "MainActivity";
    private ArrayAdapter spinnerAdapter;
    private String currentLocation;
    private String destination;
    private int method;
    int currentLocationPosition;
    int destinationPosition;
    boolean doubleBackToExitPressedOnce = false;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get ViewModel instance
        locationsViewModelContract = ViewModelProviders.of(this).get(LocationsViewModel.class);


        //Initializing Views with ButterKnife
        ButterKnife.bind(this);

        //Spinner Settings
        setSpinner();


        //Set the Car button to be checked by default
        car_button.setChecked(true);

        //Set Button Listener
        button_get_result.setOnClickListener(this);

        //Set RadioGroup Listener
        radioGroup.setOnCheckedChangeListener(this);

        //Navigation Drawer Settings
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Shared Preference and Locations List
        tinyDB = new TinyDB(this);
        if (tinyDB.getListString("locations").size() == 0) {
            Log.d(TAG, "onCreate: OnlineData");
            locationsViewModelContract.getAllLocations(this);
        } else {
            Log.d(TAG, "onCreate: OfflineData");
            ArrayList<String> locationsList = tinyDB.getListString("locations");
            spinnerAdapter.clear();
            spinnerAdapter.addAll(locationsList);
            spinnerAdapter.notifyDataSetChanged();
        }

        //Generate Or Get User Unique ID
        uniqueID = id(this);
    }// end onCreate();

    //Spinner Settings
    private void setSpinner() {
        //Adapters For Spinners
        spinnerAdapter = new ArrayAdapter(this, R.layout.my_simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.my_simple_spinner_item);
        spinner_current_location.setAdapter(spinnerAdapter);
        spinner_destination.setAdapter(spinnerAdapter);

        //Set Spinner Listeners
        spinner_current_location.setOnItemSelectedListener(this);
        spinner_destination.setOnItemSelectedListener(this);
    }// end setSpinners

    //* * * * * * * * * * Button OnClicks  and Utility Methods * * * * * * * * * * * *
    public void swapLocations(View view) {
        // Swap Selections In The Spinners
        spinner_current_location.setSelection(destinationPosition);
        spinner_destination.setSelection(currentLocationPosition);
    }

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

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);

            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }

        return uniqueID;
    }


    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //
    @Override
    public void onClick(View v) {
        Log.i("test", "result button clicked!");
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("currentLocation", currentLocation);
        resultIntent.putExtra("destination", destination);
        resultIntent.putExtra("method", method);
        resultIntent.putExtra("uniqueID", uniqueID);
        startActivity(resultIntent);
    } // end On Click

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String parentId = parent.toString().substring(103, parent.toString().length() - 1);

        if (parentId.equals("spinner_current_location")) {
            Log.i("test chosen from", "spinner_current_location");
            currentLocationPosition = position;
            currentLocation = parent.getItemAtPosition(position).toString();
        } else {
            Log.i("test chosen from", "spinner_destination");
            destinationPosition = position;
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

    @Override
    public void locationsListCallback(List<Location> locations) {
        ArrayList<String> locationsList = new ArrayList<>();
        for (Location location : locations) {
            locationsList.add(location.getName());
        }
        tinyDB.putListString("locations", locationsList);
        spinnerAdapter.clear();
        spinnerAdapter.addAll(locationsList);
        spinnerAdapter.notifyDataSetChanged();
    }

}// end MainActivity
