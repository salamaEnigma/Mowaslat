package com.paramgy.mowaslatdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.paramgy.mowaslatdemo.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity implements MvvmView {
    private static final int SPLASH_TIMEOUT = 3000;
    int progress;
    Handler mHandler = new Handler();
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView logo = findViewById(R.id.logo);

        logo.animate().alpha(1f).setDuration(SPLASH_TIMEOUT);
        final SeekBar seekBar = findViewById(R.id.seekBar);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i("timer", "25 ms");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setProgress(progress);
                    }
                });
                progress += 1;
            }
        }, 500, 25);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                timer.cancel();
            }
        }, SPLASH_TIMEOUT);


    }


}
