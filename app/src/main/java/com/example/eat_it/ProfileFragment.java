package com.example.eat_it;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.zip.Inflater;


public class ProfileFragment extends Fragment {
    TextView fullnametitle;
    TextInputLayout fullname, email, username, phoneno;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        fullnametitle = view.findViewById(R.id.titleText);
        fullname = view.findViewById(R.id.fullnametext);
        email = view.findViewById(R.id.emailtext);
        username = view.findViewById(R.id.usernametext);
        phoneno = view.findViewById(R.id.phonenotext);


         //showUserData();
        return view;



    }

     private void showUserData() {

         Bundle bundle = this.getArguments();
         if (bundle != null) {
             String nameFromDb = bundle.getString("fname");
             String usernameFromDb = bundle.getString("rUsername");
             String emailFromDb = bundle.getString("rEmail");
             String phoneFromDb = bundle.getString("rPhoneno");
             fullnametitle.setText(nameFromDb);
             fullname.getEditText().setText(nameFromDb);
             email.getEditText().setText(emailFromDb);
             phoneno.getEditText().setText(phoneFromDb);
             username.getEditText().setText(usernameFromDb);
         }


     }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }






  /*  protected void onCreateView(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        showAllUserData();
    }

   /* private void showAllUserData() {
        Intent intent= getActivity().getIntent();
        String user_username= intent.getStringExtra("rUsername");
        String user_email= intent.getStringExtra("rEmail");
        String user_phoneno= intent.getStringExtra("rPhoneno");
        String user_fullname= intent.getStringExtra("fname");

        LayoutInflater lf= getActivity().getLayoutInflater();


        fullnametitle.setText(user_fullname);
        fullname.getEditText().setText(user_fullname);
        email.getEditText().setText(user_email);
        phoneno.getEditText().setText(user_phoneno);
        username.getEditText().setText(user_username);

    }*/
}
