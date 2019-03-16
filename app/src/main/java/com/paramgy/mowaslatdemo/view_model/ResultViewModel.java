package com.paramgy.mowaslatdemo.view_model;

import android.app.Application;
import com.paramgy.mowaslatdemo.data.model.Result;
import com.paramgy.mowaslatdemo.data.repository.AppRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ResultViewModel  extends AndroidViewModel implements ResultViewModelInterface {


    //Result Object
    private Result resultObject ;

    // Error MSGs Fields
    private static final String ERROR_MSG_1 = "عاوز تروح نفس المكان اللي انت فيه؟!";
    private static final String ERROR_MSG_2 = "لسه مضفناش ( المكان / المواصلة ) لقاعدة البيانات";
    private static final String ERROR_MSG_3 = "Please restart the app and try again";


    private   AppRepository appRepository ;
    public ResultViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }

    @Override
    public String getResult(String current, String destination, int method) {
            if(current == null || destination == null) {
              //Return ErrorMsg 3 Problem in fetching result
                return ERROR_MSG_3;
            }
            else {
                //Fetch The Result
                 resultObject =  appRepository.getResult(current, destination, method);
                if (resultObject != null) {
                    String result = resultObject.getResult();
                    // Return The Result ...............
                    return result;
                } else {
                    if (current.equals(destination)) {
                       //Return Same location Error
                        return ERROR_MSG_1;
                    }
                   //Return Data not added yet error
                    return ERROR_MSG_2;
                }
            }
    } //end getResult

    @Override
    public double getRate() {
        if(resultObject != null){
           return resultObject.getRate();
        }
        return 0;
    }

    //DOES THIS SET THE RATE ON THE OBJECT IN MEMORY OR IN DATABASE OR BOTH ??
    @Override
    public void setUserRating(double rating) {
        if(resultObject != null){
             resultObject.setRate(rating);
        }
    }
}
