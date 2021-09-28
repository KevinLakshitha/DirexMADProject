package com.direx.direxcamerarent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button = (Button) findViewById(R.id.btnDroneH);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDroneAdmin();
            }
        });
    }
    private void openDroneAdmin() {
        Intent intent = new Intent(this, CusDroneList.class);
        startActivity(intent);
    }

}