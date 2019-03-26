package com.paramgy.mowaslat.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.data.model.Result;
import com.paramgy.mowaslat.view_model.ResultViewModel;
import com.paramgy.mowaslat.view_model.ResultViewModelInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements ResultActivityInterface {

    //Views
    @BindView(R.id.result_text_view)
    TextView resultTextView;
    @BindView(R.id.ratingBar)
    RatingBar resultRatingBar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    //ViewModel Reference
    private ResultViewModelInterface resultViewModel;

    //Result Object
    Result result;

    //Search Fields
    private String currentLocation;
    private String destination;
    private int method;

    private static final String TAG = "ResultActivity";
    // Error MSGs Fields
    private static final String ERROR_MSG_SAME_LOCATION = "عاوز تروح نفس المكان اللي إنت فيه؟!";
    private static final String ERROR_MSG_NOT_FOUND = "لسه مضفناش ( المكان / المواصلة ) لقاعدة البيانات";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Result View Model Instance
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        //Initializing Views
        ButterKnife.bind(this);

        //Get extras ( result details ) from the main activity
        currentLocation = getIntent().getStringExtra("currentLocation");
        destination = getIntent().getStringExtra("destination");
        method = getIntent().getIntExtra("method", 0);

        //Show and Check Result On Create
        checkResult();

        //set rating bar listener
        resultRatingBar.setOnRatingBarChangeListener(this);

    } // end on create

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    public void checkResult() {
        if (currentLocation.equals(destination)) {
            resultTextView.setText(ERROR_MSG_SAME_LOCATION);
            resultTextView.setVisibility(View.VISIBLE);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        resultViewModel.getResult(this, currentLocation, destination, method);
    }// end checkResult


    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //

    @Override
    public void setUserRating(float userRating) {
        if (result != null) {
            resultViewModel.setUserRating(userRating, result.getDocumentID());
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //set user rating
        if (fromUser) {
            setUserRating(rating);
        }
    }

    @Override
    public void resultCallback(Result result) {
        if (result != null) {
            this.result = result;
            String text = result.getText();
            resultTextView.setText(text);
        } else {
            resultTextView.setText(ERROR_MSG_NOT_FOUND);
        }
        progressBar.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
    }// end resultCallback

}
