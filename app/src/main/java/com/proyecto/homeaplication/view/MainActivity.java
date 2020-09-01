package com.proyecto.homeaplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.proyecto.homeaplication.R;
import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements IChat.MainView {


    private IChat.MainPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenter = new MainActivityPresenter(this);
        iPresenter.createUser();

    }

    @Override
    public void getUser(String name) {
        Log.e("FERNANDO",name);
    }
}
