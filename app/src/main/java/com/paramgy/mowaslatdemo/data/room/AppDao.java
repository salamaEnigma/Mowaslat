package com.paramgy.mowaslatdemo.data.room;

import com.paramgy.mowaslatdemo.data.model.Location;
import com.paramgy.mowaslatdemo.data.model.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AppDao {

    @Query("SELECT * FROM location_table")
    LiveData<List<Location>> getAllLocations();

    @Query("SELECT * FROM result_table WHERE currentLocation LIKE :current AND destination LIKE :destination AND transportation_method LIKE :method")
    Result getResult(String current, String destination, int method);

    @Insert
    void insertLocation(Location location);

    @Insert
    void insertResult(Result result);

    @Query("DELETE FROM location_table")
    void deleteAllLocations();

    @Query("DELETE FROM result_table")
    void deleteAllResults();


}
