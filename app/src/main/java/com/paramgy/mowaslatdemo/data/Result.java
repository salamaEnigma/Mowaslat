package com.paramgy.mowaslatdemo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "result_table")
public class Result {

    @PrimaryKey(autoGenerate = true)
    private long result_id ;

    private String currentLocation;
    private String destination;
    //0 For Car , 1 For Train , 2 For Tram/Metro
    private int transportation_method;
    private String result;

    public Result(String currentLocation, String destination, int transportation_method, String result) {
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.transportation_method = transportation_method;
        this.result = result;
    }


    public long getResult_id() {
        return result_id;
    }
    public void setResult_id(long result_id) {
        this.result_id = result_id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getDestination() {
        return destination;
    }

    public int getTransportation_method() {
        return transportation_method;
    }

    public String getResult() {
        return result;
    }
}
