package com.proyecto.homeaplication.interfaces;

import android.content.Context;

public interface IChat {

    interface MainView {
        void getUser(String name);
    }
    interface MainPresenter {
        void getUser(String name);
        void createUser(Context context, String name, String lastName, String email, String password, String phone);
    }
    interface MainModel {
        void createUser(Context context, String name, String lastName, String email, String password, String phone );
    }


}
