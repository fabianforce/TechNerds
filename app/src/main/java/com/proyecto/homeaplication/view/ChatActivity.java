package com.proyecto.homeaplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.proyecto.homeaplication.R;

public class ChatActivity extends AppCompatActivity {

    TextView nameLabel,emailLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        nameLabel = findViewById(R.id.name_label);
        emailLabel = findViewById(R.id.email_label);

        Intent intent = getIntent();
        String name = intent.getStringExtra("dname");
        String email = intent.getStringExtra("demail");
        nameLabel.setText(name);
        emailLabel.setText(email);
    }
}
