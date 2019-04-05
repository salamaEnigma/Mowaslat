package com.paramgy.mowaslat.features.result.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.data.model.pojos.NewResult;
import com.paramgy.mowaslat.features.message.view.MessageActivity;
import com.paramgy.mowaslat.features.result.contracts.ResultViewContract;
import com.paramgy.mowaslat.features.result.contracts.ResultViewModelContract;
import com.paramgy.mowaslat.features.result.viewmodel.ResultViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements ResultViewContract {

    //Views
    @BindView(R.id.result_text_view)
    TextView resultTextView;
    @BindView(R.id.ratingBar)
    RatingBar resultRatingBar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    //ViewModel Reference
    private ResultViewModelContract resultViewModel;


    //NewResult Object
    NewResult newResult;

    //Search Fields
    private String currentLocation;
    private String destination;
    private String method;

    private String uniqueID;


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

        //Get extras ( newResult details ) from the main activity
        currentLocation = getIntent().getStringExtra("currentLocation");
        destination = getIntent().getStringExtra("destination");
        method = getIntent().getStringExtra("method");

        //Get uniqueID
        uniqueID = getIntent().getStringExtra("uniqueID");


        //Show and Check Result On Create
        checkResult();

        //set rating bar listener
        resultRatingBar.setOnRatingBarChangeListener(this);
    } // end on create


    // * * * * * * * * * * Button onClicks and Utility Methods * * * * * * * * * * //

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    public void checkResult() {
        if (currentLocation.equals(destination)) {
            resultTextView.setText(ERROR_MSG_SAME_LOCATION);
            resultTextView.setVisibility(View.VISIBLE);
            return;
        }
        // Get Result
        LiveData<NewResult> resultLiveData = resultViewModel.getNewResult(currentLocation, destination, method);
        resultLiveData.observe(this, this);
        progressBar.setVisibility(View.VISIBLE);
    }// end checkResult

    public void setUserRating(float userRating) {
        if (newResult != null) {
            resultViewModel.setUserRating(userRating, newResult.getDocumentID(), uniqueID);
            if (userRating <= 2) {
                Intent intent = new Intent(this, MessageActivity.class);
                intent.putExtra("isBadResult", true);
                intent.putExtra("current", currentLocation);
                intent.putExtra("destination", destination);
                intent.putExtra("method", method);
                startActivity(intent);
            }
        }
    }

    // * * * * * * * * * * Interface Implementations * * * * * * * * * * //


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //set user rating
        if (fromUser) {
            setUserRating(rating);
        }
    }

    @Override
    public void onChanged(NewResult newResult) {
        if (newResult != null) {
            this.newResult = newResult;
            String text = newResult.getText();
            resultTextView.setText(text);
        } else {
            resultTextView.setText(ERROR_MSG_NOT_FOUND);
        }
        progressBar.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
    }
} //end resultActivity
