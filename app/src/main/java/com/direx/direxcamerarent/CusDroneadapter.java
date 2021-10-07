package com.direx.direxcamerarent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CusDroneadapter extends RecyclerView.Adapter<CusDroneadapter.MyViewHolder>{

    Context context;
    ArrayList<Drone> list;

    public CusDroneadapter(Context context, ArrayList<Drone> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cusdroneitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CusDroneadapter.MyViewHolder holder, int position) {

        Drone drone = list.get(position);
        holder.droneid.setText(drone.getDroneid());
        holder.title.setText(drone.getTitle());
        holder.price.setText(drone.getPrice());
        holder.description.setText(drone.getDescription());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView droneid, title, price, description;

        Button btnRentDrone;
//rent button and view

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            droneid = itemView.findViewById(R.id.Ldroneid);
            title = itemView.findViewById(R.id.Ltitle);
            price = itemView.findViewById(R.id.Lprice);
            description = itemView.findViewById(R.id.Ldescription);

            btnRentDrone = (Button)itemView.findViewById(R.id.btnRentDrone);


        }
    }


}

