package com.proyecto.homeaplication.interfaces;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface IChat {

    interface MainView {
        void getUser(String name);
    }
    interface MainPresenter {
        void getUser(String name);
        void createUser(Context context, String name, String lastName, String email, String password, String phone);
        void signInWithEmail(String email, String password);
    }
    interface MainModel {
        void createUser(Context context, String name, String lastName, String email, String password, String phone );
        void signInWithEmail(String email, String password);
        void getMyContacs(RecyclerView recyclerView);
    }

    interface HomeView {
    }

    interface HomePresenter {
        void getMyContacs(RecyclerView recyclerView);
    }


}
