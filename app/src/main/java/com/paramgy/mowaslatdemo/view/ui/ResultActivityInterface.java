package com.paramgy.mowaslatdemo.view.ui;

import android.widget.RatingBar;

public interface ResultActivityInterface extends RatingBar.OnRatingBarChangeListener {

    void displayResult();
    void setUserRating(float userRating);
}
