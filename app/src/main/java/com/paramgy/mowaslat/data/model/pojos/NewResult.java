package com.paramgy.mowaslat.data.model.pojos;

import com.google.firebase.firestore.Exclude;

public class NewResult {

    private String documentID;
    private String text;
    private double time;
    private float cost;
    private static final String ERROR_MSG_NOT_FOUND = "لسه مضفناش ( المواصلة ) دي لقاعدة البيانات";


    public NewResult() {
        //REQUIRED by Firebase
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getText() {
        if (text == null)
            return ERROR_MSG_NOT_FOUND;
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
