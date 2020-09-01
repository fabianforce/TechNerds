package com.proyecto.homeaplication.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.proyecto.homeaplication.R;
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
    private String idContact;
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

    public User(String name, String lastName, String email, String password, String phone, String idContact) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.idContact = idContact;
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

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    @Override
    public void createUser(final Context context, final EditText name, final EditText lastName, final EditText email, final EditText password, final EditText phone, final TextView loginLabel, final Button registerButton, final Button singInBtn, final TextView registerLabel) {


        if(!email.getText().toString().equals("") && !password.getText().toString().equals(""))
        {
            mAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.e("Created", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        User person = new User(name.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString());
                        mDatabase.child("users").child(user.getUid()).setValue(person);
                        name.setVisibility(View.GONE);
                        lastName.setVisibility(View.GONE);
                        phone.setVisibility(View.GONE);
                        loginLabel.setVisibility(View.GONE);
                        registerButton.setVisibility(View.GONE);
                        singInBtn.setVisibility(View.VISIBLE);
                        registerLabel.setVisibility(View.VISIBLE);
                    } else {
                        // If sign in fails, display a message to the user.
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.e("ERROR", task.getException().getMessage());
                        Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        //mDatabase.child("users").child(user.getUid()).setValue(user1);

                    }


                }
            });
        }else
        {
            Toast.makeText(context,"Empty values",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void signInWithEmail(String email, String password) {
        if (!email.equals("") && !password.equals("")) {
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
        } else {
            Log.e("VACIO", "VACIO");
        }
    }

    @Override
    public void getMyContacs(final RecyclerView recyclerView) {

        userList = new ArrayList<>();
        HomeActivity homeActivity = new HomeActivity();
        User user;

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(homeActivity, 2);
        final ContactsAdapter contactsAdapter = new ContactsAdapter(userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.e("MIERDA", currentFirebaseUser.getUid());

        mDatabase.child("users").orderByChild("idContact").equalTo(currentFirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ds.getValue().equals("name");
                    Log.i("MyTag", ds.getValue().toString());
                    User user = new User(ds.child("name").getValue().toString(), ds.child("lastName").getValue().toString(), ds.child("email").getValue().toString(), "", ds.child("phone").getValue().toString());
                    userList.add(user);

                }
                recyclerView.setAdapter(contactsAdapter);
                contactsAdapter.setOnItemClickListener(new ContactsAdapter.onItemClickChatListener() {
                    @Override
                    public void showChat(User user) {
                        ihomePresenter.openChat(user);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read app title value.", error.toException());
            }
        });

    }

    @Override
    public void setUpModal(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_modal);
        final EditText nameTxt = dialog.findViewById(R.id.name_text);
        final EditText lastNameTxt = dialog.findViewById(R.id.lastname_text);
        final EditText email = dialog.findViewById(R.id.email_text);
        final EditText phone = dialog.findViewById(R.id.phone_text);

        Button dialogButton = dialog.findViewById(R.id.buttonOk);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (!nameTxt.getText().toString().equals("") && !lastNameTxt.getText().toString().equals("") && !email.getText().toString().equals("") && !phone.getText().toString().equals("")) {
                    User person = new User(nameTxt.getText().toString(), lastNameTxt.getText().toString(), email.getText().toString(), "", phone.getText().toString(), currentFirebaseUser.getUid());
                    mDatabase.child("users").push().setValue(person);
                    dialog.dismiss();
                } else {

                    Toast toast1 = Toast.makeText(context,
                            "Campos Vacios", Toast.LENGTH_SHORT);
                    toast1.show();
                    dialog.dismiss();
                }


            }
        });
        dialog.show();
    }

    @Override
    public void setUpSettingsModal(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.settings_modal);
        final EditText nameTxt = dialog.findViewById(R.id.name_text);
        final EditText lastNameTxt = dialog.findViewById(R.id.lastname_text);
        final EditText email = dialog.findViewById(R.id.email_text);
        final EditText phone = dialog.findViewById(R.id.phone_text);

        Button dialogButton = dialog.findViewById(R.id.buttonOk);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (!nameTxt.getText().toString().equals("") && !lastNameTxt.getText().toString().equals("") && !email.getText().toString().equals("") && !phone.getText().toString().equals("")) {
                    User person = new User(nameTxt.getText().toString(), lastNameTxt.getText().toString(), email.getText().toString(), "", phone.getText().toString(), currentFirebaseUser.getUid());
                    mDatabase.child("users").child(currentFirebaseUser.getUid()).setValue(person);
                    ihomePresenter.getUser(nameTxt.getText().toString());
                    Toast toast1 = Toast.makeText(context,
                            "Campos Actualizados Correctamente", Toast.LENGTH_SHORT);
                    dialog.dismiss();
                } else {

                    Toast toast1 = Toast.makeText(context,
                            "Campos Vacios", Toast.LENGTH_SHORT);
                    toast1.show();
                    dialog.dismiss();
                }


            }
        });
        dialog.show();
    }
}
