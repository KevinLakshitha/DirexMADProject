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

public class CustomerCameraadapter extends RecyclerView.Adapter<CustomerCameraadapter.MyViewHolder> {
    Context context;
    ArrayList<Camera> list;

    public CustomerCameraadapter (Context context, ArrayList<Camera> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customercameraitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerCameraadapter.MyViewHolder holder, int position) {
        Camera camera = list.get(position);
        holder.cameraID.setText(camera.getcameraID());
        holder.cameratitle.setText(camera.getCameratitle());
        holder.cameraprice.setText(camera.getCameraprice());
        holder.camerades.setText(camera.getCamerades());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cameraID, cameratitle, cameraprice, camerades;

        Button btnRentL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cameraID = itemView.findViewById(R.id.CID);
            cameratitle = itemView.findViewById(R.id.etCameraTitle);
            cameraprice = itemView.findViewById(R.id.camprice);
            camerades = itemView.findViewById(R.id.camdis);

            btnRentL = (Button)itemView.findViewById(R.id.btnRentL);


        }
    }


}