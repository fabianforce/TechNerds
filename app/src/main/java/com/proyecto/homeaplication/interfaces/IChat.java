package com.proyecto.homeaplication.interfaces;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.homeaplication.model.User;

public interface IChat {

    interface MainView {
        void getUser(String name);
    }
    interface MainPresenter {
        void getUser(String name);
        void createUser(Context context, EditText name, EditText lastName, EditText email, EditText password, EditText phone, TextView loginLabel, Button registerButton, Button singInBtn, TextView registerLabel);
        void signInWithEmail(String email, String password);
    }
    interface MainModel {
        void createUser(Context context, EditText name, EditText lastName, EditText email, EditText password, EditText phone, TextView loginLabel, Button registerButton, Button singInBtn, TextView registerLabel);
        void signInWithEmail(String email, String password);
        void getMyContacs(RecyclerView recyclerView);
        void setUpModal(Context context);
        void setUpSettingsModal(Context context);
    }

    interface HomeView {

        void openChat(User user);
        void getUser(String name);
    }

    interface HomePresenter {

        void openChat(User user);
        void getMyContacs(RecyclerView recyclerView);
        void setUpModal(Context context);
        void setUpSettingsModal(Context context);
        void getUser(String name);
    }


}
