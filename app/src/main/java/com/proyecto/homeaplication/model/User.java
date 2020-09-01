package com.proyecto.homeaplication.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.homeaplication.interfaces.IChat;

public class User implements  IChat.MainModel {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private IChat.MainPresenter imainPresenter;
    private FirebaseAuth mAuth;
    private Context context;
    private DatabaseReference mDatabase;



    public User()
    {

    }

    public User(IChat.MainPresenter mainPresenter)
    {
        this.imainPresenter = mainPresenter;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public User(String name, String lastName, String email, String password,String phone) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void createUser(Context context, final String name, final String lastName, final String email, String password, final String phone) {
        mAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e("Created", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    User person = new User(name,lastName,email,"",phone);
                    mDatabase.child("users").child(user.getUid()).setValue(person);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("faile", task.getException().getMessage());
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.e("String", user.getUid());
                    User person = new User(name,lastName,email,"",phone);
                    //mDatabase.child("users").child(user.getUid()).setValue(user1);

                }


            }
        });

        imainPresenter.getUser("Fabito");
    }
}
