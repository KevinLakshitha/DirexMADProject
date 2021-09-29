package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerSinglecamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_camera_list);

        TextView cameraID = findViewById(R.id.CID);
        TextView cameratitle = findViewById(R.id.camtitle);
        TextView cameraprice = findViewById(R.id.camprice);
        TextView camerades = findViewById(R.id.camdis);

        String lensidExtra = getIntent().getExtras().get("lensid").toString();
        String lenstitleExtra = getIntent().getExtras().get("lenstitle").toString();
        String lenspriceExtra = getIntent().getExtras().get("lensprice").toString();
        String lensdisExtra = getIntent().getExtras().get("lensdis").toString();

        cameraID.setText(lensidExtra);
        cameratitle.setText(lenstitleExtra);
        cameraprice.setText(lenspriceExtra);
        camerades.setText(lensdisExtra);

    }
}