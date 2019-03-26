package com.paramgy.mowaslat.view_model;

import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.repository.FirestoreCallback;

import java.util.List;

import javax.security.auth.callback.Callback;

import androidx.lifecycle.LiveData;

public interface AppViewModelInterface {
    //Get List Of all Locations in the Database
    void getAllLocations(FirestoreCallback callback);

}
