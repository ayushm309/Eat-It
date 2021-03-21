package com.example.eat_it;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.*;

public class cartadapterclass extends FirebaseRecyclerAdapter<cartmodel, cartadapterclass.mycartviewholder> {


    public cartadapterclass(@NonNull FirebaseRecyclerOptions<cartmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final mycartviewholder holder, int position, @NonNull final cartmodel model) {
        holder.cartmenuname.setText(model.getMname());
        holder.cartmenuprice.setText(model.getPrice());
        holder.quantitynum.setText(model.getQuantity());

        int AllTotalPrice = 0;
        int Overallprice = ((Integer.parseInt(model.getPrice()))) * ((Integer.parseInt(model.getQuantity())));
        AllTotalPrice= AllTotalPrice + Overallprice ;




        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
                cartListRef.child("Admin User").child(Prevalent.currentOnlineUser)
                        .child("Products").child(model.getMname()).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(holder.deletebtn.getContext(), "Items Deleted", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            }
        });

    }

    @NonNull
    @Override
    public mycartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow,parent,false);



        return new cartadapterclass.mycartviewholder(view);

    }

    public class mycartviewholder extends RecyclerView.ViewHolder {

        TextView cartmenuname, cartmenuprice, quantitynum,quantityText;
        ImageButton deletebtn;
        View rootview;
        private Context context;


        public mycartviewholder(@NonNull View itemView) {
            super(itemView);
            cartmenuname=itemView.findViewById(R.id.cartmenuname);
            cartmenuprice=itemView.findViewById(R.id.cartmenuprice);
            quantitynum=itemView.findViewById(R.id.quantitynum);
            deletebtn=itemView.findViewById(R.id.deletebtn);
            quantityText=itemView.findViewById(R.id.quantitytext);


        }
    }
}
