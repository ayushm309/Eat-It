package com.example.eat_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout regName, regUser, regEmail, regPassword, regPhoneno ;
    Button goBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        //initialization
        regName = findViewById(R.id.fullname);
        regUser = findViewById(R.id.username);
        regEmail = findViewById(R.id.emailregis);
        regPassword = findViewById(R.id.passwordregis);
        regPhoneno = findViewById(R.id.phoneno);
        goBtn = findViewById(R.id.gobutton);



        //Save Data in Firebase through the button
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
        if(!validatename() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }

                //assigning the strings vars to the objects
                String fname = regName.getEditText().getText().toString();
                String rUsername = regUser.getEditText().getText().toString();
                String rEmail = regEmail.getEditText().getText().toString();
                String rPassword = regPassword.getEditText().getText().toString();
                String rPhoneno = regPhoneno.getEditText().getText().toString();
                UserHelper userHelper = new UserHelper(fname,rUsername,rEmail,rPassword,rPhoneno);

                reference.child(rUsername).setValue(userHelper);
               Toast.makeText(RegisterActivity.this,"Registration Done",Toast.LENGTH_LONG).show();

            }
        });




        }

    private Boolean validatename() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field should not be Empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername() {
        String val = regUser.getEditText().getText().toString();


        if (val.isEmpty()) {
            regUser.setError("Field should not be Empty");
            return false;
        }
        else if (val.length()>=15) {
            regUser.setError("Username is too Long");
            return false;
        }

        else{
            regUser.setError(null);
            regUser.setErrorEnabled(false);
            return true;
        }




    }
    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern;
        emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";


        if (val.isEmpty()) {
            regEmail.setError("Field should not be Empty");
            return false;
        }else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        }

        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }




    }
    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "[^-\\s]" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field should not be Empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            regPassword.setError("Password should contain 1 special character,number");
            return false;

        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }




    }
    private Boolean validatePhoneNo() {
        String val = regPhoneno.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneno.setError("Field should not be Empty");
            return false;
        }


        else{
            regPhoneno.setError(null);
            regPhoneno.setErrorEnabled(false);
            return true;
        }




    }




}




