package com.direx.direxcamerarent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        return  new Droneadapter.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull Droneadapter.MyViewHolder holder, int position) {

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


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            droneid = itemView.findViewById(R.id.Ldroneid);
            title = itemView.findViewById(R.id.Ltitle);
            price = itemView.findViewById(R.id.Lprice);
            description = itemView.findViewById(R.id.Ldescription);

        }
    }

}
