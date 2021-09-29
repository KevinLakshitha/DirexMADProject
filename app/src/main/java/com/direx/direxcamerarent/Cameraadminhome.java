package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Cameraadminhome extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameraadminhome);

        button = (Button) findViewById(R.id.btnCameraAH);
        button = (Button) findViewById(R.id.btnCushome);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
    }
    private void openDroneAdmin() {
        Intent intent = new Intent(this, AdminCamera.class);
        startActivity(intent);
    }

}