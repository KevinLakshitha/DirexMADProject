package com.direx.direxcamerarent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteDrone extends AppCompatActivity {

    EditText droneid;
    Button droneDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_drone);
       droneid = findViewById(R.id.etDeleteDrone);
       droneDelete = findViewById(R.id.btnDeletedrone);



       droneDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String Droneid = droneid.getText().toString().trim();
                if (!Droneid.isEmpty()){

                    deleteData(Droneid);

                }else{

                    Toast.makeText(DeleteDrone.this,"Please Enter DroneID",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void deleteData(String droneid) {

        reference = FirebaseDatabase.getInstance().getReference("drone").child(droneid);
        reference.removeValue();
        Toast.makeText(DeleteDrone.this,"Successfully Deleted.",Toast.LENGTH_SHORT).show();

    }
}