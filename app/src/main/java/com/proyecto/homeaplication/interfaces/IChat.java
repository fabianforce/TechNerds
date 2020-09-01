package com.proyecto.homeaplication.interfaces;

public interface IChat {

    interface MainView {
        void getUser(String name);
    }
    interface MainPresenter {
        void getUser(String name);
        void createUser();
    }
    interface MainModel {
        void createUser();
    }


}
