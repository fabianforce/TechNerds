package com.proyecto.homeaplication.presenter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.model.User;

public class HomeActivityPresenter implements IChat.HomePresenter {

    private IChat.HomeView iView;
    private IChat.MainModel iModel;


    public HomeActivityPresenter(IChat.HomeView view)
    {
        this.iView = view;
        iModel = new User(this);
    }

    @Override
    public void openChat(User user) {
        if (iView != null) {
            iView.openChat(user);
        }
    }

    @Override
    public void getMyContacs(RecyclerView recyclerView) {
        if (iView != null) {
            iModel.getMyContacs(recyclerView);
        }
    }

    @Override
    public void setUpModal(Context context) {
        if (iView != null) {
            iModel.setUpModal(context);
        }
    }

    @Override
    public void setUpSettingsModal(Context context) {
        if (iView != null) {
            iModel.setUpSettingsModal(context);
        }
    }

    @Override
    public void getUser(String name) {
        if (iView != null) {
            iView.getUser(name);
        }
    }
}
