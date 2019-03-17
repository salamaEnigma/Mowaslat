package com.paramgy.mowaslatdemo.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "result_table")
public class Result {

    @PrimaryKey(autoGenerate = true)
    private long resultId;

    private String currentLocation;
    private String destination;
    //0 For Car , 1 For Train , 2 For Tram/Metro
    private int transportationMethod;
    private String result;

    public Result(String currentLocation, String destination, int transportationMethod, String result) {
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.transportationMethod = transportationMethod;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getDestination() {
        return destination;
    }

    public int getTransportationMethod() {
        return transportationMethod;
    }

}
