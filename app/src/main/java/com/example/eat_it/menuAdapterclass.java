package com.example.eat_it;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class menuAdapterclass extends FirebaseRecyclerAdapter<menumodel, menuAdapterclass.myviewholder2> {


    public menuAdapterclass(@NonNull FirebaseRecyclerOptions<menumodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull menumodel model) {
        holder.menuname.setText(model.getMname());
        holder.menuprice.setText("Rs " + model.getPrice());


    }

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menurow,parent,false);
        return new myviewholder2(view);
    }

    public class myviewholder2 extends RecyclerView.ViewHolder {

        TextView menuname, menuprice;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            menuname=itemView.findViewById(R.id.menuname);
            menuprice=itemView.findViewById(R.id.menuprice);

        }
    }
}
