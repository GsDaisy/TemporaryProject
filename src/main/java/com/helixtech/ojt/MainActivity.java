package com.helixtech.ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button gmapBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gmapBtn = findViewById(R.id.gmap_btn);
        gmapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmapIntent = new Intent(getApplicationContext(), GoogleMapsActivity.class);
                startActivity(gmapIntent);
            }
        });
    }
}
