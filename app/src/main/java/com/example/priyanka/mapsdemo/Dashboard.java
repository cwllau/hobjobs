package com.example.priyanka.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Button btnToListings = (Button)findViewById(R.id.btnToListings);
        Button btnToInbox = (Button)findViewById(R.id.btnToInbox );
        btnToListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, JobListings.class));
            }});
    }
}

