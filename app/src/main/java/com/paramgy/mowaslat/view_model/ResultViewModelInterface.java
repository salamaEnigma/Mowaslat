package com.paramgy.mowaslat.view_model;

import com.paramgy.mowaslat.data.firestore.FirestoreResultCallback;

public interface ResultViewModelInterface  {

    void getResult(FirestoreResultCallback callback ,String current, String destination, int method);

    void setUserRating(float rating , String resultID, String userID);
}
