package com.paramgy.mowaslatdemo.view_model;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.paramgy.mowaslatdemo.data.model.Result;
import com.paramgy.mowaslatdemo.data.repository.AppRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ResultViewModel extends AndroidViewModel implements ResultViewModelInterface {

    private Context applicationContext;

    //Result Object
    private Result resultObject;
    private long resultID;
    private String resultString;

    // Error MSGs Fields
    private static final String ERROR_MSG_SAME_LOCATION = "عاوز تروح نفس المكان اللي انت فيه؟!";
    private static final String ERROR_MSG_NOT_FOUND = "لسه مضفناش ( المكان / المواصلة ) لقاعدة البيانات";
    private static final String ERROR_MSG_DATA_SOURCE = "Please restart the app and try again";


    private AppRepository appRepository;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        applicationContext = application.getApplicationContext();
        appRepository = AppRepository.getInstance(application);
    }

    public Result getResultObject(String current, String destination, int method) {
        Result result = appRepository.getResult(current, destination, method);
        if (result != null) {
            resultID = result.getResultId();
        }
        return result;
    }


    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //

    @Override
    public String getResultString(String current, String destination, int method) {
        if (current == null || destination == null) {
            //Return ErrorMsg Data Source Problem in fetching result
            return ERROR_MSG_DATA_SOURCE;
        } else {
            //Fetch The Result
            resultObject = getResultObject(current, destination, method);
            if (resultObject != null) {
                resultString = resultObject.getResult();
                // Return The Result ...............
                return resultString;
            } else {
                if (current.equals(destination)) {
                    //Return Same location Error
                    return ERROR_MSG_SAME_LOCATION;
                }
                //Return Data not added yet error
                return ERROR_MSG_NOT_FOUND;
            }
        }
    } //end getResultString

    /*
    DOES THIS SET THE RATE ON THE OBJECT IN MEMORY OR IN DATABASE OR BOTH ??
    Answer: Only In Memory man :) !
    ** Good Debugging Enigma :D !
    */

    @Override
    public void setUserRating(float rating) {
                /*
                 Set Result Rating Somewhere Somehow XD !
                 and make update only if the result already exists and has a string text result
                 */
        if (resultObject != null && resultString != null) {
            Log.i("Result ID", resultID + "");
            Log.i("Result User Rating", rating + "");
            Log.i("Result Rate Update", "done!");
            Toast.makeText(applicationContext,"Your feedback is appreciated :)",Toast.LENGTH_SHORT)
                    .show();
        }
    } //end set user rating
}

