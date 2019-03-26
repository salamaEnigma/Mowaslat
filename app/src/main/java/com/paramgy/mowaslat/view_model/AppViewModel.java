package com.paramgy.mowaslat.view_model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.paramgy.mowaslat.data.firestore.TinyDB;
import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.data.repository.FirestoreCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel implements AppViewModelInterface {

    private AppRepository appRepository;
    private static final String TAG = "AppViewModel";


    public AppViewModel() {
        appRepository = AppRepository.getInstance();
    }


    public void getAllLocations(FirestoreCallback callback) {
        appRepository.getAllLocations(callback) ;
    }// end GetAllLocations

}
