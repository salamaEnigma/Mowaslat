package com.paramgy.mowaslat.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.view_model.ResultViewModel;
import com.paramgy.mowaslat.view_model.ResultViewModelInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class ResultActivity extends AppCompatActivity implements ResultActivityInterface {

    //Views
    TextView resultTextView;
    RatingBar resultRatingBar;

    //ViewModel Reference
    private ResultViewModelInterface resultViewModel;

    //Search Fields
    private String currentLocation;
    private String destination;
    private int method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Result View Model Instance
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        //Initializing Views
        resultTextView = findViewById(R.id.result_text_view);
        resultRatingBar = findViewById(R.id.ratingBar);

        //Get extras ( result details ) from the main activity
        currentLocation = getIntent().getStringExtra("currentLocation");
        destination = getIntent().getStringExtra("destination");
        method = getIntent().getIntExtra("method", 0);


        //Show Result On Create
        displayResult();

        //set rating bar listener
        resultRatingBar.setOnRatingBarChangeListener(this);

    } // end on create

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //

    @Override
    public void displayResult() {
        String result = resultViewModel.getResultString(currentLocation, destination, method);
        resultTextView.setText(result);
    }

    @Override
    public void setUserRating(float userRating) {
        resultViewModel.setUserRating(userRating);
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //set user rating
        if (fromUser) {
            setUserRating(rating);
        }
    }

}
