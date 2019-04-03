package com.paramgy.mowaslat.features.result.contracts;

import com.paramgy.mowaslat.data.model.pojos.Result;

import androidx.lifecycle.LiveData;

public interface ResultViewModelContract {

    LiveData<Result> getResult(String current, String destination, int method);

    void setUserRating(float rating, String resultID, String userID);
}
