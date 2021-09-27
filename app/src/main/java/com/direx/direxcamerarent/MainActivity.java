package com.direx.direxcamerarent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dronelist);


        final EditText editnameU = findViewById(R.id.editnameU);
        final EditText editaddressU = findViewById(R.id.editaddressU);
        Button btn = findViewById(R.id.btnsubmitU);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editnameU.getText().toString().trim();
                String address = editaddressU.getText().toString().trim();

                DatabaseReference ref = database.getReference("user");
                User user = new User(name,address);
                ref.child(name).setValue(user);

                Toast.makeText(MainActivity.this,"Created Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}