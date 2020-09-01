package com.proyecto.homeaplication.presenter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.model.User;

public class MainActivityPresenter implements IChat.MainPresenter {

    private IChat.MainView iView;
    private IChat.MainModel iModel;


   public MainActivityPresenter(IChat.MainView view)
   {
       this.iView = view;
       iModel = new User(this);
   }

    @Override
    public void getUser(String name) {
        if (iView != null) {
            iView.getUser(name);
        }
    }

    @Override
    public void createUser(Context context, String name, String lastName, String email, String password, String phone) {
        if (iView != null) {
            iModel.createUser(context,name,lastName,email,password,phone);
        }
    }

    @Override
    public void signInWithEmail(String email, String password) {
        if (iView != null) {
            iModel.signInWithEmail(email,password);
        }
    }

}
