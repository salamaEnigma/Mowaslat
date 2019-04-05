package com.paramgy.mowaslat.features.result.contracts;

import com.paramgy.mowaslat.data.model.pojos.NewResult;

import androidx.lifecycle.LiveData;

public interface ResultViewModelContract {

    LiveData<NewResult> getNewResult(String current, String destination,String method);

    void setUserRating(float rating, String resultID, String userID);
}
