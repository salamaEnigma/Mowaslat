package com.paramgy.mowaslat.features.result.contracts;

import android.widget.RatingBar;

import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreResultCallback;

public interface ResultViewContract extends RatingBar.OnRatingBarChangeListener , FirestoreResultCallback {
}
