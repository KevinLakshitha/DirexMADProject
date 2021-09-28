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

public class CustomerLensadapter extends RecyclerView.Adapter<CustomerLensadapter.MyViewHolder> {
    Context context;
    ArrayList<Lens> list;

    public CustomerLensadapter(Context context, ArrayList<Lens> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customerlensitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerLensadapter.MyViewHolder holder, int position) {
        Lens lens = list.get(position);
        holder.lensid.setText(lens.getLensid());
        holder.lenstitle.setText(lens.getLenstitle());
        holder.lensprice.setText(lens.getLensprice());
        holder.lensdis.setText(lens.getLensdis());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lensid, lenstitle, lensprice, lensdis;

        Button btnRentL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lensid = itemView.findViewById(R.id.LLid);
            lenstitle = itemView.findViewById(R.id.LLtitle);
            lensprice = itemView.findViewById(R.id.LLprice);
            lensdis = itemView.findViewById(R.id.LLdis);

            btnRentL = (Button)itemView.findViewById(R.id.btnRentL);


        }
    }


}
