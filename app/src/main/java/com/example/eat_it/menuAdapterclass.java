package com.example.eat_it;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull final menumodel model) {
        holder.menuname.setText(model.getMname());
        holder.menuprice.setText("Rs " + model.getPrice());
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogcart= LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.addcartdialog,null);
                Button addcart;
                addcart=dialogcart.findViewById(R.id.addcart);
                TextView proname= dialogcart.findViewById(R.id.proname);
                TextView proprice= dialogcart.findViewById(R.id.proprice);
                TextView quantity= dialogcart.findViewById(R.id.quantitytext);
                final int[] count = {1};
                final TextView value= dialogcart.findViewById(R.id.qnvalue);
                ImageButton addbtn = dialogcart.findViewById(R.id.addbtn);
                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                       /*int count = 1;*/
                       count[0]++;
                       value.setText("" + count[0]);




                    }
                });
                ImageButton  subbtn = dialogcart.findViewById(R.id.subbtn);
                subbtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                      /*  int count = 1;*/

                        if (count[0] <=1)
                        {
                            count[0] =1;
                        }
                        else count[0]--;
                        value.setText("" + count[0]);

                    }
                });

                proname.setText(model.getMname());
                proprice.setText(model.getPrice());
                builder.setView(dialogcart);
                builder.setCancelable(true);
                builder.show();


            }
        });




    }

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menurow,parent,false);



        return new myviewholder2(view);
    }

    public class myviewholder2 extends RecyclerView.ViewHolder {

        TextView menuname, menuprice;
        ImageButton addtocart;
        Dialog dialogcart;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            menuname=itemView.findViewById(R.id.menuname);
            menuprice=itemView.findViewById(R.id.menuprice);
            addtocart=itemView.findViewById(R.id.addToCart);





        }
    }
}
