package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CusDroneList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CusDroneadapter myAdapter;
    ArrayList<Drone> list;
    EditText Searchet;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_drone_list);

        recyclerView = findViewById(R.id.CusRVDronelist);
        Searchet = findViewById(R.id.etSearch);
        database = FirebaseDatabase.getInstance().getReference("drone");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapter = new CusDroneadapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Drone drone = dataSnapshot.getValue(Drone.class);
                    list.add(drone);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Searchet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

                if(Searchet.getText().toString().equals("")){
                    recyclerView = findViewById(R.id.CusRVDronelist);
                    Searchet = findViewById(R.id.etSearch);
                    database = FirebaseDatabase.getInstance().getReference("drone");
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CusDroneList.this));

                    list = new ArrayList<>();

                    myAdapter = new CusDroneadapter(CusDroneList.this, list);
                    recyclerView.setAdapter(myAdapter);
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                Drone drone = dataSnapshot.getValue(Drone.class);
                                list.add(drone);
                            }
                           myAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }
                else {
                    str = charSequence.toString();
                    onStartdrone();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    protected void onStartdrone() {
        super.onStart();
         FirebaseRecyclerOptions<Drone> options = null;
         if(str.equals("")){
             options = new FirebaseRecyclerOptions.Builder<Drone>().setQuery(database,Drone.class).build();
         }else{
             options = new FirebaseRecyclerOptions.Builder<Drone>().setQuery(database.orderByChild("title").startAt(str).endAt(str + "\uf8ff"),Drone.class).build();
         }
         FirebaseRecyclerAdapter<Drone, CusDroneadapter.MyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Drone, CusDroneadapter.MyViewHolder>(options) {
             @Override
             protected void onBindViewHolder(@NonNull CusDroneadapter.MyViewHolder myViewHolder, int i, @NonNull Drone drone) {
                 myViewHolder.droneid.setText(drone.getDroneid());
                 myViewHolder.title.setText(drone.getTitle());
                 myViewHolder.price.setText(drone.getPrice());
                 myViewHolder.description.setText(drone.getDescription());

                 myViewHolder.btnRentDrone.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent (CusDroneList.this,CusDroneSingleview.class);
                         intent.putExtra("droneid",drone.getDroneid());
                         intent.putExtra("title",drone.getTitle());
                         intent.putExtra("price",drone.getPrice());
                         intent.putExtra("description",drone.getDescription());
                         startActivity(intent);
                     }
                 });
             }

             @NonNull
             @Override
             public CusDroneadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusdroneitem,parent,false);
                 CusDroneadapter.MyViewHolder viewHolder = new CusDroneadapter.MyViewHolder(view);
                 return viewHolder;
             }
         };
         recyclerView.setAdapter(firebaseRecyclerAdapter);
         firebaseRecyclerAdapter.startListening();;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView droneid, title, price, description;

        Button btnRentDrone;


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



