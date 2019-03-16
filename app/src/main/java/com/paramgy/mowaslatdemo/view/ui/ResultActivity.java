package com.paramgy.mowaslatdemo.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.paramgy.mowaslatdemo.R;
import com.paramgy.mowaslatdemo.view_model.ResultViewModel;
import com.paramgy.mowaslatdemo.view_model.ResultViewModelInterface;

public class ResultActivity extends AppCompatActivity implements ResultActivityInterface {

    //Fields
    private ResultViewModelInterface resultViewModel;
    private String currentLocation;
    private String destination;
    private int method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Result View Model Instance
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        //Get extras ( result details ) from the main activity
        currentLocation = getIntent().getStringExtra("currentLocation");
        destination = getIntent().getStringExtra("destination");
        method = getIntent().getIntExtra("method", 0);


        //Show Result On Create
        displayResult();
    }

    @Override
    public void displayResult() {
        String result = resultViewModel.getResult(currentLocation,destination,method);
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUserRating() {

    }

    @Override
    public void onClick(View v) {

    }

}
