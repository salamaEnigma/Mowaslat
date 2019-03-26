package com.paramgy.mowaslat.view_model;

import android.util.Log;

import com.paramgy.mowaslat.data.firestore.FirestoreResultCallback;
import com.paramgy.mowaslat.data.repository.AppRepository;

import androidx.lifecycle.ViewModel;

public class ResultViewModel extends ViewModel implements ResultViewModelInterface {

    private AppRepository appRepository;

    public ResultViewModel() {
        appRepository = AppRepository.getInstance();
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
    public void setUserRating(float rating, String resultID) {
                /*
                 Set Result Rating Somewhere Somehow XD !
                 and make update only if the result already exists and has a string text result
                 */
        Log.i("Result ID", resultID + "");
        Log.i("Result User Rating", rating + "");
        Log.i("Result Rate Update", "done!");

    } //end set user rating
}

