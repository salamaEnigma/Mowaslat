package com.paramgy.mowaslat.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.paramgy.mowaslat.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {

    //Views
    @BindView(R.id.editText) EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Initializing Views
        ButterKnife.bind(this);

        boolean isBadResult = getIntent().getBooleanExtra("isBadResult",false);

        if(isBadResult){
            editText.setHint("اقترح طريق أفضل");
        }
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
