package com.paramgy.mowaslatdemo.view_model;

import com.paramgy.mowaslatdemo.data.model.Result;

public interface ResultViewModelInterface {

    String getResultString(String current, String destination, int method);

    void setUserRating(float rating);
}
