package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LensAdmin extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_admin);

        final EditText ETAlensid = findViewById(R.id.ETAlensid);
        final EditText ETAlenstitle = findViewById(R.id.ETAlenstitle);
        final EditText ETAlensprice = findViewById(R.id.ETAlensprice);
        final EditText ETAlensdis = findViewById(R.id.ETAlensdis);


        Button btn = findViewById(R.id.BTAadd);

        Button btn2 = findViewById(R.id.btnviewL);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            opennext();


            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lensid = ETAlensid.getText().toString().trim();
                String lenstitle = ETAlenstitle.getText().toString().trim();
                String lensprice = ETAlensprice.getText().toString().trim();
                String lensdis = ETAlensdis.getText().toString().trim();

               /* validateInfo(lensdis); */

                DatabaseReference ref = database.getReference("lens");
                Lens lens = new Lens(lensid,lenstitle,lensprice,lensdis);
                ref.child(lensid).setValue(lens);

                Toast.makeText(LensAdmin.this,"Created Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void opennext() {
        Intent intent1 = new Intent(this, Lenslist.class);
        startActivity(intent1);
    }

/*
    private Boolean validateInfo(String lensdis){
        if(lensdis!="canon mount" || lensdis!="nikon mount" || lensdis!="sony mount"){
            ETAlensdis.requestFocus();
            ETAlensdis.setError("please enter mount type");
            return false;

        }
    }   */
}