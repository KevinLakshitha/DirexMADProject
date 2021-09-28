package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminhomeL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome_l);

        Button Lenses = findViewById(R.id.btnLensAH);

        Button Cushome = findViewById(R.id.btnCushome);

        Lenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opennext();


            }
        });

        Cushome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opennext1();


            }
        });
    }


    public void opennext() {
        Intent intent1 = new Intent(this, LensAdmin.class);
        startActivity(intent1);
    }

    public void opennext1() {
        Intent intent2 = new Intent(this, CustomerLensList.class);
        startActivity(intent2);
    }
}