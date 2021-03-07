package com.example.eat_it;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class menuAdapterclass extends FirebaseRecyclerAdapter<menumodel, menuAdapterclass.myviewholder2> {


    public menuAdapterclass(@NonNull FirebaseRecyclerOptions<menumodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder2 holder, int position, @NonNull final menumodel model) {
        holder.menuname.setText(model.getMname());
        holder.menuprice.setText("Rs " + model.getPrice());
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogcart= LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.addcartdialog,null);
                Button addcart;
                addcart=dialogcart.findViewById(R.id.addcart);
                final TextView proname= dialogcart.findViewById(R.id.proname);
                final TextView proprice= dialogcart.findViewById(R.id.proprice);
                TextView quantity= dialogcart.findViewById(R.id.quantitytext);
                final TextView value= dialogcart.findViewById(R.id.qnvalue);



                final int[] count = {1};

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
                addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String savecurrenttime, savecurrentdate;
                      Class<UserHelper> usermodel = UserHelper.class;
                        Calendar calfordate= Calendar.getInstance();





                        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd,yy");
                        savecurrentdate = currentDate.format(calfordate.getTime());
                        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
                        savecurrenttime = currentTime.format(calfordate.getTime());


                        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");






                        final HashMap<String, Object> cartMap = new HashMap<>();
                        cartMap.put("mname",proname.getText().toString());
                        cartMap.put("price",proprice.getText().toString());
                        cartMap.put("Quantity",value.getText().toString());//Integer.parseInt
                        cartMap.put("date",savecurrentdate );
                        cartMap.put("time",savecurrenttime);


                        cartListRef.child("Admin User").child(Prevalent.currentOnlineUser)
                                .child("Products").child(proname.getText().toString())
                                .updateChildren(cartMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {


                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(holder.addtocart.getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();



                                    }
                                });



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
