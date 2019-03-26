package com.paramgy.mowaslat.view.ui;

import android.widget.RatingBar;

public interface ResultActivityInterface extends RatingBar.OnRatingBarChangeListener {

    void displayResult();
    void setUserRating(float userRating);
}
