package com.proyecto.homeaplication.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.model.User;

public class HomeActivityPresenter implements IChat.HomePresenter {

    private IChat.HomeView iView;
    private IChat.MainModel iModel;


    public HomeActivityPresenter(IChat.HomeView view) {
        this.iView = view;
        iModel = new User(this);
    }

    @Override
    public void getMyContacs(RecyclerView recyclerView) {
        if (iView != null) {
            iModel.getMyContacs(recyclerView);
        }
    }
}
