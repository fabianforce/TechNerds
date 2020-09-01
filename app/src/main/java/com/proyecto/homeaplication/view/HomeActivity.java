package com.proyecto.homeaplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.proyecto.homeaplication.R;
import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.presenter.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements IChat.HomeView {

    TextView nameLabel;
    RecyclerView contactsRecycler;
    private IChat.HomePresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        iPresenter = new HomeActivityPresenter(this);
        nameLabel = findViewById(R.id.hname_label);
        contactsRecycler = findViewById(R.id.contact_recycler);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        nameLabel.setText(email);
        iPresenter.getMyContacs(contactsRecycler);
    }
}
