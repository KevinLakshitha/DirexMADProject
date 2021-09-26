package com.direx.direxcamerarent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateDrone extends AppCompatActivity {

    EditText lensid;
    EditText title;
    EditText price;
    EditText description;
    Button updateList;

    public UpdateDrone(EditText lensid, EditText title, EditText price, EditText description) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lens);
        lensid = findViewById(R.id.etDroneIDUpdate);
        title = findViewById(R.id.etTitledroneUpdate);
        price = findViewById(R.id.etpricedroneUpdate);
        description = findViewById(R.id.etdesDroneUpdate);


        updateLens = findViewById(R.id.btnUpdatedrone);



        updateDrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Droneid = droneid.getText().toString().trim();
                String Dtitle = title.getText().toString().trim();
                String Dprice = price.getText().toString().trim();
                String Ddescription = description.getText().toString().trim();

                new UpdateDrone(droneid,title,price,description);

            }

        });
    }

    private void UpdateDrone(String droneid, String title, String price, String description) {

        HashMap Drone = new HashMap();
        Drone.put("droneid",droneid);
        Drone.put("title",title);
        Drone.put("droneid",droneid);
        Drone.put("droneid",droneid);



        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("drone").child(droneid);

        Toast.makeText(UpdateDrone.this,"Successfully Updated.",Toast.LENGTH_SHORT).show();



    }


}