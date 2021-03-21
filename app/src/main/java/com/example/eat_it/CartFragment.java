package com.example.eat_it;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class CartFragment extends Fragment {
    RecyclerView cartrec;
    cartadapterclass cartadapterclass;
    TextView totalcarttext;


    public CartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        totalcarttext=view.findViewById(R.id.cardpricetext);




        cartrec=(RecyclerView)view.findViewById(R.id.cartrecy);
        cartrec.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<cartmodel> options =
                new FirebaseRecyclerOptions.Builder<cartmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart List")
                                .child("Admin User")
                                .child(Prevalent.currentOnlineUser)
                                .child("Products"), cartmodel.class)
                        .build();
        cartadapterclass= new cartadapterclass(options);
        cartrec.setAdapter(cartadapterclass);
        cartadapterclass.startListening();
        totalcarttext.setText(Prevalent.totalPrice);
        /*Bundle bundle=getArguments();
        if (bundle!=null) {
            totalprice = bundle.getString("TP");
        }*/



        return view;
    }

}