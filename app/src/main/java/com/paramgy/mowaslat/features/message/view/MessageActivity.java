package com.paramgy.mowaslat.features.message.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.paramgy.mowaslat.R;
import com.paramgy.mowaslat.features.message.contracts.MessageViewContract;
import com.paramgy.mowaslat.features.message.contracts.MessageViewModelContract;
import com.paramgy.mowaslat.features.message.viewmodel.MessageViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements MessageViewContract {

    //Views
    @BindView(R.id.editText)
    EditText editText;

    //Fields
    MessageViewModelContract messageViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Initializing Views
        ButterKnife.bind(this);

        //MessageViewModel instance
        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);


        boolean isBadResult = getIntent().getBooleanExtra("isBadResult", false);
        if (isBadResult) {
            editText.setHint("اقترح طريق أفضل");
        }
    } // end on create

    public void closeButtonClicked(View view) {
        onBackPressed();
    }

    public void sendButtonClicked(View view) {
        String message = editText.getText().toString();
        //TODO Some action to send a msg
        Log.i("User Message", message);
        messageViewModel.sendMsg(message);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();

    }
}
