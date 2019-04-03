package com.paramgy.mowaslat.features.result.contracts;

import android.widget.RatingBar;

import com.paramgy.mowaslat.data.model.pojos.Result;

import androidx.lifecycle.Observer;

public interface ResultViewContract extends RatingBar.OnRatingBarChangeListener, Observer<Result> {
}
