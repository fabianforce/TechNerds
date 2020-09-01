package com.proyecto.homeaplication.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.homeaplication.adapter.ContactsAdapter;
import com.proyecto.homeaplication.interfaces.IChat;
import com.proyecto.homeaplication.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class User implements IChat.MainModel {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private IChat.MainPresenter imainPresenter;
    private IChat.HomePresenter ihomePresenter;
    private FirebaseAuth mAuth;
    private Context context;
    private DatabaseReference mDatabase;
    List<User> userList;


    public User() {

    }

    public User(IChat.MainPresenter mainPresenter) {
        this.imainPresenter = mainPresenter;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public User(IChat.HomePresenter homePresenter) {
        this.ihomePresenter = homePresenter;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public User(String name, String lastName, String email, String password, String phone) {
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
        mAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e("Created", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    User person = new User(name, lastName, email, "", phone);
                    mDatabase.child("users").child(user.getUid()).setValue(person);
                } else {
                    // If sign in fails, display a message to the user.
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.e("ERROR", task.getException().getMessage());
                    //mDatabase.child("users").child(user.getUid()).setValue(user1);

                }


            }
        });
    }

    @Override
    public void signInWithEmail(String email, String password) {
        if(!email.equals("") && !password.equals(""))
        {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            imainPresenter.getUser(user.getEmail());
                        } else {
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                        }

                    }
                });
        }else
        {
            Log.e("VACIO","VACIO");
        }
    }

    @Override
    public void getMyContacs(final RecyclerView recyclerView) {

        userList = new ArrayList<>();
        HomeActivity homeActivity = new HomeActivity();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(homeActivity, 2);
        final ContactsAdapter contactsAdapter = new ContactsAdapter(userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    ds.getValue().equals("name");
                    Log.i("MyTag", ds.getValue().toString());
                    User user = new User(ds.child("name").getValue().toString(),ds.child("lastName").getValue().toString(),ds.child("email").getValue().toString(),"",ds.child("phone").getValue().toString());
                    userList.add(user);

                }
                recyclerView.setAdapter(contactsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read app title value.", error.toException());
            }
        });

    }
}
