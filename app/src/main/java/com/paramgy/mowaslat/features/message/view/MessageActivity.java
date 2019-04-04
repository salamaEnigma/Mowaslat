package com.paramgy.mowaslat.features.message.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.features.message.contracts.MessageViewContract;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements MessageViewContract {

    //Views
    @BindView(R.id.editText)
    EditText editText;

    //Fields
    String current;
    String destination;
    String method;
    boolean isBadResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Initializing Views
        ButterKnife.bind(this);

        isBadResult = getIntent().getBooleanExtra("isBadResult", false);
        if (isBadResult) {
            editText.setHint("اقترح طريق أفضل");
            current = getIntent().getStringExtra("current");
            destination = getIntent().getStringExtra("destination");
            method = getIntent().getStringExtra("method");

            switch (method) {
                case "car":
                    method = "مشروع";
                case "train":
                    method = "قطر أبو قير";
                case "tram":
                    method = "ترام";
            }
        }
    } // end on create

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    public void sendButtonClicked(View view) {
        String message = editText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter the message first", Toast.LENGTH_SHORT).show();
        } else {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            if (isBadResult) {
                String info = '\n' + "مكانك: " + current + '\n' + "رايح فين: " + destination + '\n' +
                        "رايح ازاي: " + method;
                message = message + info;
            }
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mowaslat.info@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "اقتراح");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }

    } //end send
}
