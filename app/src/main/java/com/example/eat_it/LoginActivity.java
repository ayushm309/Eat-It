package com.example.eat_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button callSignUp;
    Button mainPageButton;
    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.registerbutton);
        mainPageButton = findViewById(R.id.login_button);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);



        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // public void loginUser (View, view){
                if (!validateUsername() | !validatePassword()){
                        return;
                }
                else{
                        userVerify();
                    }

            }
            private void userVerify() {
                final String userNamedata = username.getText().toString().trim();
                final String passwordData = password.getText().toString().trim();

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
                Query checkUser= reference.orderByChild("rUsername").equalTo(userNamedata);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            username.setError(null);

                            String passwordFromDb =snapshot.child(userNamedata).child("rPassword").getValue(String.class);
                            if (passwordFromDb.equals(passwordData)){

                                username.setError(null);
                                String nameFromdb = snapshot.child(userNamedata).child("fname").getValue(String.class);
                                String usernameFromdb = snapshot.child(userNamedata).child("rUsername").getValue(String.class);
                                String emailFromdb = snapshot.child(userNamedata).child("rEmail").getValue(String.class);
                                String phoneFromdb = snapshot.child(userNamedata).child("rPhoneno").getValue(String.class);

                                Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
                                Bundle bundle= new Bundle();
                                bundle.putString(nameFromdb,"fname");
                                bundle.putString(usernameFromdb,"rUsername");
                                bundle.putString(emailFromdb,"rEmail");
                                bundle.putString(phoneFromdb,"rPhoneno");
                                ProfileFragment profileFragment= new ProfileFragment();
                                profileFragment.setArguments(bundle);
                                /*intent.putExtra("fname",nameFromdb);
                                intent.putExtra("rUsername",usernameFromdb);
                                intent.putExtra("rEmail",emailFromdb);
                                intent.putExtra("rPhoneno",phoneFromdb);*/


                                startActivity(intent);
                            }
                            else {
                                password.setError("Wrong Password");
                                password.requestFocus();
                            }
                        }
                        else{
                            username.setError("No such User exists");
                            username.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
    private Boolean validateUsername(){
        String val = username.getText().toString();


        if (val.isEmpty()) {
            username.setError("Field should not be Empty");
            return false;
        }
        else if (val.length()>=15) {
            username.setError("Username is too Long");
            return false;
        }

        else{
            username.setError(null);
            return true;
        }




    };
    private Boolean validatePassword() {
        String val = password.getText().toString();


        if (val.isEmpty()) {
            password.setError("Field should not be Empty");
            return false;
        }



        else{
            password.setError(null);
            return true;
        }




    };







}



