package com.direx.direxcamerarent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Droneadapter extends RecyclerView.Adapter<Droneadapter.MyViewHolder> {

    Context context;
    ArrayList<Drone> list;

    public Droneadapter(Context context, ArrayList<Drone> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Droneadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.droneitem,parent,false);
        return  new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull Droneadapter.MyViewHolder holder, int position) {

        Drone drone = list.get(position);
        holder.droneid.setText(drone.getDroneid());
        holder.title.setText(drone.getTitle());
        holder.price.setText(drone.getPrice());
        holder.description.setText(drone.getDescription());


//delete part

        holder.btnDeleteDrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("drone")
                        .child(drone.getDroneid()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.droneid.getContext()," Deleted successfully",Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context,Lenslist.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent1);
                                //dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(holder.droneid.getContext(),"Deleted data",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


//update part


        holder.btnEditDrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.droneid.getContext())
                        .setContentHolder(new ViewHolder(R.layout.droneupdate_popup))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();
                EditText droneid = view1.findViewById(R.id.etdroneidPopup);
                EditText title = view1.findViewById(R.id.etpricePopup);
                EditText price = view1.findViewById(R.id.etpricepopup);
                EditText description = view1.findViewById(R.id.etdisPopup);


                Button btnupdatedronePopup = view1.findViewById(R.id.btnUpdatedronePopup);

                droneid.setText(drone.getDroneid());
              title.setText(drone.getTitle());
                price.setText(drone.getPrice());
                description.setText(drone.getDescription());


                dialogPlus.show();


                btnupdatedronePopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Map<String,Object> map = new HashMap<>();
                        map.put("droneid",droneid.getText().toString());
                        map.put("title",title.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("description",description.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("drone")
                                .child(drone.getDroneid()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.droneid.getContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                                        //dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.droneid.getContext(),"Error updating data",Toast.LENGTH_SHORT).show();

                                    }
                                });

                    }
                });


            }


        });



    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView droneid, title, price, description;

        Button btnEditDrone,btnDeleteDrone;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            droneid = itemView.findViewById(R.id.Ldroneid);
            title = itemView.findViewById(R.id.Ltitle);
            price = itemView.findViewById(R.id.Lprice);
            description = itemView.findViewById(R.id.Ldescription);

            btnEditDrone = (Button)itemView.findViewById(R.id.btnRentDrone);
            btnDeleteDrone = (Button)itemView.findViewById(R.id.btnDeleteDrone);

        }
    }

}
