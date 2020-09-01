package com.proyecto.homeaplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.proyecto.homeaplication.R;
import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.model.User;
import com.proyecto.homeaplication.presenter.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements IChat.HomeView {

    TextView nameLabel;
    RecyclerView contactsRecycler;
    private IChat.HomePresenter iPresenter;
    Button addBtn;
    ImageButton setBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        iPresenter = new HomeActivityPresenter(this);
        nameLabel = findViewById(R.id.hname_label);
        contactsRecycler = findViewById(R.id.contact_recycler);
        addBtn = findViewById(R.id.add_btn);
        setBtn = findViewById(R.id.set_btn);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        nameLabel.setText(email);
        /**
         * method: getMyContacs()
         * description: List of all contacts
         */
        iPresenter.getMyContacs(contactsRecycler);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * method: setUpModal()
                 * description: Add a Contact and show the modal
                 */
                iPresenter.setUpModal(HomeActivity.this);
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * method: setUpSettingsModal()
                 * description: Settings Modal
                 */
                iPresenter.setUpSettingsModal(HomeActivity.this);
            }
        });


    }

    @Override
    public void openChat(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra("dname", user.getName());
        intent.putExtra("dlastName", user.getLastName());
        intent.putExtra("demail", user.getEmail());
        intent.putExtra("idCont", user.getIdContact());
        startActivity(intent);
    }

    @Override
    public void getUser(String name) {
        nameLabel.setText(name);
    }
}
