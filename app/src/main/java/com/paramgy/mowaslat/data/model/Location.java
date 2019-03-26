package com.paramgy.mowaslat.data.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.GeoPoint;

public class Location {

    //Fields
    private String name;
    private GeoPoint coordinates;
    private String documentId;

    //Constructors
    public Location() {
        //needed for the Firestore
    }

    public Location(String name, GeoPoint coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    //toString
    @Override
    public String toString() {
        return name;
    }
}

