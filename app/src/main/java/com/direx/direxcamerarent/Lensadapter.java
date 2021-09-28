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

        Lens lens = list.get(position);
        holder.lensid.setText(lens.getLensid());
        holder.lenstitle.setText(lens.getLenstitle());
        holder.lensprice.setText(lens.getLensprice());
        holder.lensdis.setText(lens.getLensdis());




        holder.btnDeleteL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("lens")
                        .child(lens.getLensid()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.lensid.getContext(),"Invoice deleted successfully",Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context,Lenslist.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent1);
                                //dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(holder.lensid.getContext(),"Invoice deleted data",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });





        holder.btnEditL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.lensdis.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();
                EditText lensid = view1.findViewById(R.id.ETUlensid);
                EditText lenstitle = view1.findViewById(R.id.ETUlenstitle);
                EditText lensprice = view1.findViewById(R.id.ETUlensprice);
                EditText lensdis = view1.findViewById(R.id.ETUlensdis);


                Button btnUpdateLL = view1.findViewById(R.id.btnUpdateLL);

                lensid.setText(lens.getLensid());
                lenstitle.setText(lens.getLenstitle());
                lensprice.setText(lens.getLensprice());
                lensdis.setText(lens.getLensdis());


                dialogPlus.show();


                btnUpdateLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Map<String,Object> map = new HashMap<>();
                        map.put("lensid",lensid.getText().toString());
                        map.put("lenstitle",lenstitle.getText().toString());
                        map.put("lensprice",lensprice.getText().toString());
                        map.put("lensdis",lensdis.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("lens")
                                .child(lens.getLensid()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                       Toast.makeText(holder.lensid.getContext(),"data updated successfully",Toast.LENGTH_SHORT).show();
                                        //dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.lensid.getContext(),"Error updating data",Toast.LENGTH_SHORT).show();

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

        TextView lensid, lenstitle, lensprice, lensdis;

        Button btnEditL,btnDeleteL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lensid = itemView.findViewById(R.id.LLid);
            lenstitle = itemView.findViewById(R.id.LLtitle);
            lensprice = itemView.findViewById(R.id.LLprice);
            lensdis = itemView.findViewById(R.id.LLdis);

            btnEditL = (Button)itemView.findViewById(R.id.btnEditL);
            btnDeleteL = (Button)itemView.findViewById(R.id.btnDeleteL);

        }
    }

}

