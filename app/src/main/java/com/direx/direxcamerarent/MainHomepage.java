package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainHomepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button btnx = findViewById(R.id.btnAdminHomepage);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opennext();
            }
        });

    }

    public void opennext(){
        Intent intent = new Intent(this,AdminHome.class);
        startActivity(intent);
    }

}