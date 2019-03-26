package com.paramgy.mowaslat.data.model;

import com.google.firebase.firestore.Exclude;

public class Result {



    private String documentID;

    private String currentLocation;
    private String destination;
    //0 For Car , 1 For Train , 2 For Tram/Metro
    private int transportationMethod;
    private String text;

    public Result() {
        //Needed for Firebase
    }

    public Result(String currentLocation, String destination, int transportationMethod, String text) {
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.transportationMethod = transportationMethod;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
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
