package com.paramgy.mowaslat.features.result.contracts;

import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreResultCallback;

public interface ResultViewModelContract {

    void getResult(FirestoreResultCallback callback ,String current, String destination, int method);

    void setUserRating(float rating , String resultID, String userID);
}
