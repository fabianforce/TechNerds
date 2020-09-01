package com.proyecto.homeaplication.presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    public void createUser(Context context, EditText name, EditText lastName, EditText email, EditText password, EditText phone, TextView loginLabel, Button registerButton,  Button singInBtn, TextView registerLabel) {
        if (iView != null) {
            iModel.createUser(context,name,lastName,email,password,phone,loginLabel,registerButton,singInBtn,registerLabel);
        }
    }

    @Override
    public void signInWithEmail(String email, String password) {
        if (iView != null) {
            iModel.signInWithEmail(email,password);
        }
    }

}
