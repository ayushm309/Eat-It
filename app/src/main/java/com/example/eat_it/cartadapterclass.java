package com.example.eat_it;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class cartadapterclass extends FirebaseRecyclerAdapter<cartmodel, cartadapterclass.mycartviewholder> {

    public cartadapterclass(@NonNull FirebaseRecyclerOptions<cartmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mycartviewholder holder, int position, @NonNull cartmodel model) {
        holder.cartmenuname.setText(model.getMname());
        holder.cartmenuprice.setText(model.getPrice());
        holder.quantitynum.setText(model.getQuantity());

    }

    @NonNull
    @Override
    public mycartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menurow,parent,false);



        return new cartadapterclass.mycartviewholder(view);

    }

    public class mycartviewholder extends RecyclerView.ViewHolder {
        TextView cartmenuname, cartmenuprice, quantitynum;

        public mycartviewholder(@NonNull View itemView) {
            super(itemView);
            cartmenuname=itemView.findViewById(R.id.cartmenuname);
            cartmenuprice=itemView.findViewById(R.id.cartmenuprice);
            quantitynum=itemView.findViewById(R.id.quantitynum);
        }
    }
}
