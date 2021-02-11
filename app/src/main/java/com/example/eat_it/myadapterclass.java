package com.example.eat_it;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapterclass extends FirebaseRecyclerAdapter<homemodel, myadapterclass.myviewholder> {



    public myadapterclass(@NonNull FirebaseRecyclerOptions<homemodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final homemodel model) {

        holder.nameresto.setText(model.getName());
        holder.type.setText(model.getTypes());
        Glide.with(holder.banner.getContext()).load(model.getPurl()).into(holder.banner);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new descFragment(model.getName(),model.getPurl(),model.getTypes())).addToBackStack(null).commit();

            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowrestp,parent,false);
        return new myviewholder(view);

    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView banner;
        TextView nameresto, type;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
            nameresto=itemView.findViewById(R.id.nameresto);
            type=itemView.findViewById(R.id.type);
        }
    }
}
