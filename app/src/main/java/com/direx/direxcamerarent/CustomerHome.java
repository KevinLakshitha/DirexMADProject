package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        Button Lenses = findViewById(R.id.btnLensCH);


        Lenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opennext();


            }
        });


    }


    public void opennext() {
        Intent intent1 = new Intent(this, CustomerLensList.class);
        startActivity(intent1);
    }
}