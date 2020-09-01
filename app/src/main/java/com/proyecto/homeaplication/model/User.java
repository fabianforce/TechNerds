package com.proyecto.homeaplication.model;

import com.proyecto.homeaplication.interfaces.IChat;

public class User implements  IChat.MainModel {

    private String name;
    private String pasword;
    private IChat.MainPresenter imainPresenter;

    public User()
    {

    }

    public User(IChat.MainPresenter mainPresenter)
    {
        this.imainPresenter = mainPresenter;
    }

    public User(String name, String pasword) {
        this.name = name;
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public void createUser() {

        imainPresenter.getUser("Fabito");
    }
}
