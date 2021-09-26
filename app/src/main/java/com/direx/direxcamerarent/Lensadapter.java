package com.direx.direxcamerarent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lensadapter extends RecyclerView.Adapter<Lensadapter.MyViewHolder> {

    Context context;

    ArrayList<Lens> list;


    public Lensadapter(Context context, ArrayList<Lens> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lensitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lens lenss = list.get(position);
        holder.lensid.setText(lenss.getLensid());
        holder.lenstitle.setText(lenss.getLenstitle());
        holder.lensprice.setText(lenss.getLensprice());
        holder.lensdis.setText(lenss.getLensdis());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lensid, lenstitle, lensprice, lensdis;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lensid = itemView.findViewById(R.id.Ldroneid);
            lenstitle = itemView.findViewById(R.id.Ltitle);
            lensprice = itemView.findViewById(R.id.Lprice);
            lensdis = itemView.findViewById(R.id.Ldescription);

        }
    }

}
