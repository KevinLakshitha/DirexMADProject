package com.direx.direxcamerarent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CusDroneSingleview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_drone_singleview);

        TextView droneid = findViewById(R.id.etDroneIDCV);
        TextView title = findViewById(R.id.etTitleCV);
        TextView price = findViewById(R.id.etpriceCV);
        TextView description = findViewById(R.id.etDesCV);

        String droneidExtra = getIntent().getExtras().get("droneid").toString();
        String titleExtra = getIntent().getExtras().get("title").toString();
        String priceExtra = getIntent().getExtras().get("price").toString();
        String disExtra = getIntent().getExtras().get("description").toString();

        droneid.setText(droneidExtra);
        title.setText(titleExtra);
        price.setText(priceExtra);
        description.setText(disExtra);

    }
}