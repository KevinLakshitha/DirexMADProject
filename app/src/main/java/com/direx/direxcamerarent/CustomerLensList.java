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
    CustomerLensadapter myAdapter;
    ArrayList<Lens> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_lens_list);

        recyclerView = findViewById(R.id.cRVlenslist);
        database = FirebaseDatabase.getInstance().getReference("lens");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new CustomerLensadapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Lens lens = dataSnapshot.getValue(Lens.class);
                    list.add(lens);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseRecyclerOptions<Lens> options = null;
        options = new FirebaseRecyclerOptions.Builder<Lens>().setQuery(database.orderByChild("lensid"),Lens.class).build();
        FirebaseRecyclerAdapter<Lens,MyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Lens, MyViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i, @NonNull Lens lens) {
                myViewHolder.lensid.setText(lens.getLensid());
                myViewHolder.lenstitle.setText(lens.getLenstitle());
                myViewHolder.lensprice.setText(lens.getLensprice());
                myViewHolder.lensdis.setText(lens.getLensdis());

                myViewHolder.btnRentL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CustomerLensList.this, CustomerSingleLens.class);
                        intent.putExtra("lensid",lens.getLensid());
                        intent.putExtra("lenstitle",lens.getLenstitle());
                        intent.putExtra("lensprice",lens.getLensprice());
                        intent.putExtra("lensdis",lens.getLensdis());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlensitem, parent, false);
                MyViewHolder viewHolder = new MyViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
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