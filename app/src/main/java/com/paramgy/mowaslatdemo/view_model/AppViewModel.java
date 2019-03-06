package com.paramgy.mowaslatdemo.view_model;

import android.app.Application;

import com.paramgy.mowaslatdemo.data.Location;
import com.paramgy.mowaslatdemo.data.Repository;
import com.paramgy.mowaslatdemo.data.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AppViewModel extends AndroidViewModel {

   private Repository repository ;

    public AppViewModel(@NonNull Application application) {
        super(application);
        //THIS NEED TO BE INJECTED (DEPENDENCY INJECTION)
        repository = new Repository(application);
    }

    public Result getResult(String current ,String destination ,int method){return repository.getResult(current,destination,method);}
    public LiveData<List<Location>> getAllLocations(){return repository.getAllLocations();}
}
