package com.proyecto.homeaplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyecto.homeaplication.R;
import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements IChat.MainView {


    private IChat.MainPresenter iPresenter;
    private FirebaseAuth mAuth;
    EditText nameText, lastNameText, emailText, passText, phoneText;
    Button registerBtn,logInBtn,addContactBtn;
    TextView registerLabel,loginLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenter = new MainActivityPresenter(this);
        mAuth = FirebaseAuth.getInstance();
        setupVariables();

    }

    @Override
    public void getUser(String name) {

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        intent.putExtra("email", name);
        startActivity(intent);
    }

    public void setupVariables() {
        registerBtn = findViewById(R.id.register_btn);
        nameText = findViewById(R.id.name_text);
        lastNameText = findViewById(R.id.lastname_text);
        emailText = findViewById(R.id.email_text);
        passText = findViewById(R.id.pass_text);
        phoneText = findViewById(R.id.phone_text);
        registerLabel = findViewById(R.id.register_label);
        loginLabel= findViewById(R.id.login_label);
        logInBtn = findViewById(R.id.sigin_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * method: createUser()
                 * description: User Register
                 */
                iPresenter.createUser(getApplicationContext(),nameText,lastNameText,emailText,passText,phoneText, loginLabel, registerBtn, logInBtn, registerLabel);
            }
        });

        registerLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText.setVisibility(View.VISIBLE);
                lastNameText.setVisibility(View.VISIBLE);
                phoneText.setVisibility(View.VISIBLE);
                registerLabel.setVisibility(View.GONE);
                logInBtn.setVisibility(View.GONE);
                loginLabel.setVisibility(View.VISIBLE);
                registerBtn.setVisibility(View.VISIBLE);
            }
        });

        loginLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameText.setVisibility(View.GONE);
                lastNameText.setVisibility(View.GONE);
                phoneText.setVisibility(View.GONE);
                registerBtn.setVisibility(View.GONE);
                registerLabel.setVisibility(View.VISIBLE);
                logInBtn.setVisibility(View.VISIBLE);

                loginLabel.setVisibility(View.GONE);
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * method: signInWithEmail()
                 * description: signIn With the Email And Password
                 */
                iPresenter.signInWithEmail(emailText.getText().toString(),passText.getText().toString());
            }
        });
    }
}
