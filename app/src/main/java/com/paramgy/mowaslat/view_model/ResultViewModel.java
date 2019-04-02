package com.paramgy.mowaslat.view_model;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.paramgy.mowaslat.data.firestore.FirestoreResultCallback;
import com.paramgy.mowaslat.data.repository.AppRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class ResultViewModel extends AndroidViewModel implements ResultViewModelInterface {

    private AppRepository appRepository;
    private Context applicationContext ;
    public ResultViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance();
        applicationContext = application.getApplicationContext();
    }

    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //

    @Override
    public void getResult(FirestoreResultCallback callback, String current, String destination, int method) {
        appRepository.getResult(callback, current, destination, method);
    } //end getResultString

    /*
    DOES THIS SET THE RATE ON THE OBJECT IN MEMORY OR IN DATABASE OR BOTH ??
    Answer: Only In Memory man :) !
    ** Good Debugging Enigma :D !
    */

    @Override
    public void setUserRating(float rating, String resultID, String userUniqueID) {
                /*
                 Set Result Rating Somewhere Somehow XD !
                 and make update only if the result already exists and has a string text result
                 */
        Log.i("Result UUID", userUniqueID);
        Log.i("Result ID", resultID + "");
        Log.i("Result User Rating", rating + "");
        Log.i("Result Rate Update", "done!");
        appRepository.setResultRating(rating,resultID,userUniqueID);
        Toast.makeText(applicationContext, "Thank you for your feedback", Toast.LENGTH_SHORT).show();

    } //end set user rating


}

