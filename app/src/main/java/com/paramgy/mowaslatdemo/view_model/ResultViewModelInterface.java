package com.paramgy.mowaslatdemo.view_model;

import com.paramgy.mowaslatdemo.data.model.Result;

public interface ResultViewModelInterface  {

   String getResult(String current , String destination , int method);
   double getRate();
    void setUserRating(double rating);
}
