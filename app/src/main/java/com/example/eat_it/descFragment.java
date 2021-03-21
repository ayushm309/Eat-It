package com.example.eat_it;

import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class descFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String name, purl, types;
    RecyclerView recmenu;
    menuAdapterclass adapterclass;
    Button rateIt;
    Dialog dialog;


    public descFragment() {

    }
    public descFragment(String name, String purl, String types) {
        this.name=name;
        this.purl=purl;
        this.types=types;


    }


    public static descFragment newInstance(String param1, String param2) {
        descFragment fragment = new descFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_desc, container, false);
        ImageView mainbanner = view.findViewById(R.id.mainbanner);
       TextView desctext = view.findViewById(R.id.desctext);
        TextView typestext = view.findViewById(R.id.typestext);
        rateIt=view.findViewById(R.id.Rateit);
        //Dialog = new Dialog((AppCompatActivity)getContext());
        dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialoag_rateit);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Button done = dialog.findViewById(R.id.donebtn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Thank You For Rating", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });




        Glide.with(getContext()).load(purl).into(mainbanner);
        desctext.setText(name);
        typestext.setText(types);



        recmenu=(RecyclerView)view.findViewById(R.id.recmenu);
        recmenu.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<menumodel> options =
                new FirebaseRecyclerOptions.Builder<menumodel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category"), menumodel.class)
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category").child("1").child("menu"), menumodel.class)
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MenuList").child(Prevalent.restoname).child("menu"), menumodel.class)
                        .build();
        adapterclass= new menuAdapterclass(options);
        recmenu.setAdapter(adapterclass);

        rateIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });




       return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterclass.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapterclass.stopListening();
    }




    public void OnBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();


    }
}