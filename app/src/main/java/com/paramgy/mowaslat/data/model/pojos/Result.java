package com.paramgy.mowaslat.data.model.pojos;

import com.google.firebase.firestore.Exclude;

public class Result {
    private String documentID;

    private String currentLocation;
    private String destination;

    //Text Results
    private String car;
    private String train;
    private String tram;
    private static final String ERROR_MSG_NOT_FOUND = "لسه مضفناش ( المواصلة ) دي لقاعدة البيانات";

    public Result() {
        //Needed for Firebase
    }

    public Result(String currentLocation, String destination, String car, String train, String tram) {
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.car = car;
        this.train = train;
        this.tram = tram;
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

    public String getCar() {
        if (car == null)
            return ERROR_MSG_NOT_FOUND;
        return car;
    }

    public String getTrain() {
        if (train == null)
            return ERROR_MSG_NOT_FOUND;
        return train;
    }


    public String getTram() {
        if (tram == null)
            return ERROR_MSG_NOT_FOUND;
        return tram;
    }

    public String getText(String method) {

        switch (method) {
            case "car":
                return getCar();

            case "train":
                return getTrain();

            case "tram":
                return getTram();

            default:
                return getCar();
        }
    }
}
