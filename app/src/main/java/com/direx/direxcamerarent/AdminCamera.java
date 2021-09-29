package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCamera extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_camera);

        final EditText etcameraIDadmin = findViewById(R.id.ETAcameraID);
        final EditText etCameraTitle = findViewById(R.id.ETAcameratitle);
        final EditText etPrice = findViewById(R.id.ETAcameraprice);
        final EditText etDes = findViewById(R.id.ETAcamerades);

        Button btnaddcameraadmin = findViewById(R.id.BTAadd);


        btnaddcameraadmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String cameraID = etcameraIDadmin.getText().toString().trim();
                String cameraTitle = etCameraTitle.getText().toString().trim();
                String cameraDes = etDes.getText().toString().trim();
                String cameraprice = etPrice.getText().toString().trim();

                /* validateInfo(lensdis); */

                DatabaseReference ref = database.getReference("camera");
                Camera camera = new Camera(cameraID,cameraTitle,cameraDes,cameraprice);
                ref.child(cameraID).setValue(camera);

                Toast.makeText(AdminCamera.this, "Created Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }}

