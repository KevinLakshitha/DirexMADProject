package com.example.direx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnAccessories)
    Button btnAccessories;
    @BindView(R.id.btnCamera)
    Button btnCamera;
    @BindView(R.id.btnDrone)
    Button btnDrone;
    @BindView(R.id.btnLens)
    Button btnLens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnAccessories.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,AddAccessoriesActivity.class)));
        btnCamera.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,AccessoriesActivity.class)));
    }
}