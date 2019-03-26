package com.paramgy.mowaslat.data.room;

import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.model.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AppDao {

    @Query("SELECT * FROM location_table")
    LiveData<List<Location>> getAllLocations();

    @Query("SELECT * FROM result_table WHERE currentLocation LIKE :current AND destination LIKE :destination AND transportationMethod LIKE :method")
    Result getResult(String current, String destination, int method);

    @Insert
    void insertLocation(Location location);

    @Insert
    void insertResult(Result result);

    @Update
    void updateResult(Result result);

    @Query("DELETE FROM location_table")
    void deleteAllLocations();

    @Query("DELETE FROM result_table")
    void deleteAllResults();


}
