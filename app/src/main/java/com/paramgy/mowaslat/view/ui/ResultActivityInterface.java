package com.paramgy.mowaslat.view.ui;

import android.widget.RatingBar;

import com.paramgy.mowaslat.data.firestore.FirestoreResultCallback;

public interface ResultActivityInterface extends RatingBar.OnRatingBarChangeListener , FirestoreResultCallback {

    void setUserRating(float userRating);
}
