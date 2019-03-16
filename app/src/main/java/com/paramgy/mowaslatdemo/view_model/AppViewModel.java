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

public class AppViewModel extends AndroidViewModel implements AppViewModelInterface {

    private AppRepository appRepository;
    private Application applicationContext;

    public AppViewModel(@NonNull Application application) {
        super(application);
        this.applicationContext = application;
        //THIS NEED TO BE INJECTED (DEPENDENCY INJECTION)
        appRepository = AppRepository.getInstance(application);
    }
    public LiveData<List<Location>> getAllLocations() {
        return appRepository.getAllLocations();
    }
}
