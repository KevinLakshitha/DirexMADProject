package com.direx.direxcamerarent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerSingleLens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_single_lens);

      TextView lensid = findViewById(R.id.CSLLid);
      TextView lenstitle = findViewById(R.id.CSLLtitle);
      TextView lensprice = findViewById(R.id.CSLLprice);
      TextView lensdis = findViewById(R.id.CSLLdis);

        String lensidExtra = getIntent().getExtras().get("lensid").toString();
        String lenstitleExtra = getIntent().getExtras().get("lenstitle").toString();
        String lenspriceExtra = getIntent().getExtras().get("lensprice").toString();
        String lensdisExtra = getIntent().getExtras().get("lensdis").toString();

      lensid.setText(lensidExtra);
      lenstitle.setText(lenstitleExtra);
      lensprice.setText(lenspriceExtra);
      lensdis.setText(lensdisExtra);

    }
}