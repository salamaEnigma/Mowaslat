package com.paramgy.mowaslat.features.message.viewmodel;

import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.features.message.contracts.MessageViewModelContract;

import androidx.lifecycle.ViewModel;

public class MessageViewModel extends ViewModel implements MessageViewModelContract {


    private AppRepository appRepository;

    public MessageViewModel() {
        appRepository = AppRepository.getInstance();
    }

    @Override
    public void sendMsg(String text) {
        appRepository.sendMsg(text);
    }
}
