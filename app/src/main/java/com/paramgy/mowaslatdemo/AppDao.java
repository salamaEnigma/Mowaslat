package com.paramgy.mowaslatdemo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AppDao {

    @Query("SELECT * FROM location_table")
    List<Location> getAllLocations();

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
