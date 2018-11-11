package com.example.priyanka.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobListings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listings);

        Button btn1 = (Button)findViewById(R.id.job1);
        Button btn2 = (Button)findViewById(R.id.job2);
        Button btn3 = (Button)findViewById(R.id.job3);
        Button btnToMap = (Button)findViewById(R.id.btnToMap);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListings.this, SingleJobListing.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListings.this, SingleJobListing.class));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListings.this, SingleJobListing.class));
            }
        });
        btnToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListings.this, MapsActivity.class));
            }
        });

    }
}
