package com.paramgy.mowaslat.features.result.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.paramgy.mowaslat.data.model.pojos.Result;
import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.features.result.contracts.ResultViewModelContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ResultViewModel extends AndroidViewModel implements ResultViewModelContract {

    private AppRepository appRepository;
    private Context applicationContext;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance();
        applicationContext = application.getApplicationContext();
    }

    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //

    @Override
    public LiveData<Result> getResult(String current, String destination, int method) {
        return appRepository.getResult(current, destination, method);
    } //end getResultString

    @Override
    public void setUserRating(float rating, String resultID, String uniqueID) {

        appRepository.setResultRating(rating, resultID, uniqueID);
        Toast.makeText(applicationContext, "Thank you for your feedback", Toast.LENGTH_SHORT).show();

    } //end set user rating


}

