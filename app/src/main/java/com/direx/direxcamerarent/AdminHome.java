package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHome extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

     button = (Button) findViewById(R.id.btnDroneAH);
     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             openDroneAdmin();
         }
     });
    }
         private void openDroneAdmin() {
             Intent intent = new Intent(this, DroneAdmin.class);
             startActivity(intent);
         }

}