package com.paramgy.mowaslat.features.result.contracts;

import android.widget.RatingBar;

import com.paramgy.mowaslat.data.model.pojos.NewResult;

import androidx.lifecycle.Observer;

public interface ResultViewContract extends RatingBar.OnRatingBarChangeListener, Observer<NewResult> {
}
