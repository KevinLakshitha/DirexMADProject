package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainHomedirex extends AppCompatActivity {


    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_homedirex);


        button1 = findViewById(R.id.btnAdminHomepage);
        button2 = findViewById(R.id.btnCusHomepage);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainHomedirex.this, AdminHome.class);
                startActivity(intent);


            }


        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainHomedirex.this, HomePage.class);
                startActivity(intent);


            }


        });

    }
}