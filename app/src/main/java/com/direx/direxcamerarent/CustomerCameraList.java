package com.direx.direxcamerarent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerLensList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CustomerCameraadapter myAdapter;
    ArrayList<Camera> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_camera_list);

        recyclerView = findViewById(R.id.cRVlenslist);
        database = FirebaseDatabase.getInstance().getReference("camera");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new CustomerCameraadapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Camera camera = dataSnapshot.getValue(Camera.class);
                    list.add(camera);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseRecyclerOptions<Camera> options = null;
        options = new FirebaseRecyclerOptions.Builder<Camera>().setQuery(database.orderByChild("cameraID"),Camera.class).build();
        FirebaseRecyclerAdapter<Camera,MyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Camera, MyViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i, @NonNull Camera camera) {
                myViewHolder.camID.setText(camera.getcameraID());
                myViewHolder.camtitle.setText(camera.getCameratitle());
                myViewHolder.camprice.setText(camera.getCameraprice());
                myViewHolder.camdes.setText(camera.getCamerades());

                myViewHolder.btnRentL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CustomerLensList.this, camerasingleview.class);
                        intent.putExtra("cameraID",camera.getcameraID());
                        intent.putExtra("cameratitle",camera.getCameratitle());
                        intent.putExtra("cameraprice",camera.getCameraprice());
                        intent.putExtra("camerades",camera.getCamerades());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customercameraitem, parent, false);
                MyViewHolder viewHolder = new MyViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView camID, camtitle, camprice, camdes;

        Button btnRentL;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            camID = itemView.findViewById(R.id.CID);
            camtitle = itemView.findViewById(R.id.camtitle);
            camprice = itemView.findViewById(R.id.camprice);
            camdes = itemView.findViewById(R.id.camdis);

            btnRentL = (Button)itemView.findViewById(R.id.btnRentL);


        }
    }
}