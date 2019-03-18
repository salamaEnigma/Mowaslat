package com.paramgy.mowaslatdemo.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.paramgy.mowaslatdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    //Views
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Initializing Views
        editText = findViewById(R.id.editText);
    }

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    public void sendButtonClicked(View view) {
        String message = editText.getText().toString();
        Log.i("User Message", message);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();

    }
}
