package com.direx.direxcamerarent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DroneAdmin extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_admin);

        final EditText etDroneIDAdmin = findViewById(R.id.etDroneIDAdmin);
        final EditText etTitleAdmin = findViewById(R.id.etTitleAdmin);
        final EditText etPriceAdmin = findViewById(R.id.etPriceAdmin);
        final EditText etDescriptionAdmin = findViewById(R.id.etDescriptionAdmin);
        Button btn = findViewById(R.id.btnAddDroneADMIN);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String droneid = etDroneIDAdmin.getText().toString().trim();
                String title = etTitleAdmin.getText().toString().trim();
                String price = etPriceAdmin.getText().toString().trim();
                String description = etDescriptionAdmin.getText().toString().trim();

                DatabaseReference ref = database.getReference("drone");
                Drone drone = new Drone(droneid,title,price,description);
                ref.child(droneid).setValue(drone);

                Toast.makeText(DroneAdmin.this,"Created Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}