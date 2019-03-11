package com.paramgy.mowaslatdemo.view_model;

import android.app.Application;
import android.widget.Toast;

import com.paramgy.mowaslatdemo.data.model.Location;
import com.paramgy.mowaslatdemo.data.repository.AppRepository;
import com.paramgy.mowaslatdemo.data.model.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AppViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private Application applicationContext;

    // Error MSGs Fields
    private static final String ERROR_MSG_1 = "عاوز تروح نفس المكان اللي انت فيه؟!";
    private static final String ERROR_MSG_2 = "لسه مضفناش ( المكان / المواصلة ) لقاعدة البيانات";
    private static final String ERROR_MSG_3 = "Please restart the app and try again";

    public AppViewModel(@NonNull Application application) {
        super(application);
        this.applicationContext = application;
        //THIS NEED TO BE INJECTED (DEPENDENCY INJECTION)
        appRepository = AppRepository.getInstance(application);

    }

    public void getResult(String currentLocation, String destination, int method) {
        if(currentLocation == null || destination == null) {
            Toast.makeText(applicationContext, ERROR_MSG_3, Toast.LENGTH_SHORT).show();
        }
        else {
            Result resultObject =  appRepository.getResult(currentLocation, destination, method);
            if (resultObject != null) {
                String result = resultObject.getResult();
                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show();
            } else {
                if (currentLocation.equals(destination)) {
                    Toast.makeText(applicationContext, ERROR_MSG_1, Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(applicationContext, ERROR_MSG_2, Toast.LENGTH_SHORT).show();
            }
        }
    } // end getResult

    public LiveData<List<Location>> getAllLocations() {
        return appRepository.getAllLocations();
    }
}
