package com.paramgy.mowaslatdemo;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AppViewModel extends AndroidViewModel {

   private Repository repository ;

    public AppViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

    }

    public void insertLocation(Location location){repository.insertLocation(location);}
    public void insertResult(Result result){repository.insertResult(result);}
    public void deleteAllLocations(){repository.deleteAllLocation();}
    public void deleteAllResults(){repository.deleteAllResults();}

    public Result getResult(String current ,String destination ,int method){return repository.getResult(current,destination,method);}
    public List<Location> getAllLocations(){return repository.getAllLocations();}
}
