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

public class Cameraadapter extends RecyclerView.Adapter<Cameraadapter.MyViewHolder> {
    Context context;
    ArrayList<Camera> list;

    public Cameraadapter(Context context, ArrayList<Camera> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cameraitem,parent,false);
        return  new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Camera camera = list.get(position);
        holder.cameraID.setText(camera.getcameraID());
        holder.cameratitle.setText(camera.getCameratitle());
        holder.cameraprice.setText(camera.getCameraprice());
        holder.camerades.setText(camera.getCamerades());




        holder.btnDeleteL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("camera")
                        .child(camera.getcameraID()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.cameraID.getContext(),"Invoice deleted successfully",Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context,cameralist.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent1);
                                //dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(holder.cameraID.getContext(),"Invoice deleted data",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });





        holder.btnEditL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.camerades.getContext())
                        .setContentHolder(new ViewHolder(R.layout.camera_popup))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();
                EditText cameraID = view1.findViewById(R.id.ETUcamid);
                EditText cameratitle = view1.findViewById(R.id.ETUcamtitle);
                EditText cameraprice = view1.findViewById(R.id.ETUcamprice);
                EditText camerades = view1.findViewById(R.id.ETUcamdis);


                Button btnUpdateLL = view1.findViewById(R.id.btnUpdateLL);

                cameraID.setText(camera.getcameraID());
                cameratitle.setText(camera.getCameratitle());
                cameraprice.setText(camera.getCameraprice());
                camerades.setText(camera.getCamerades());


                dialogPlus.show();


                btnUpdateLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Map<String,Object> map = new HashMap<>();
                        map.put("cameraid",cameraID.getText().toString());
                        map.put("cameratitle",cameratitle.getText().toString());
                        map.put("cameraprice",cameraprice.getText().toString());
                        map.put("camerades",camerades.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("camera")
                                .child(camera.getcameraID()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.cameraID.getContext(),"data updated successfully",Toast.LENGTH_SHORT).show();
                                        //dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.cameraID.getContext(),"Error updating data",Toast.LENGTH_SHORT).show();

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

        TextView cameraID, cameratitle, cameraprice, camerades;

        Button btnEditL,btnDeleteL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cameraID = itemView.findViewById(R.id.CamidAdview);
            cameratitle = itemView.findViewById(R.id.CamtitleAdview);
            cameraprice = itemView.findViewById(R.id.CampriceAdview);
            camerades = itemView.findViewById(R.id.CamdesAdview);

            btnEditL = (Button)itemView.findViewById(R.id.btnUpdateLL);
            btnDeleteL = (Button)itemView.findViewById(R.id.btnDeleteL);

        }
    }

}