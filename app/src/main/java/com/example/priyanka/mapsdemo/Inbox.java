package com.example.priyanka.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);


        Button btnToListings = (Button)findViewById(R.id.btnToListings);
        Button btnToDash = (Button)findViewById(R.id.btnToDash);

        btnToListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inbox.this, JobListings.class));
            }
        });

        btnToDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inbox.this, Dashboard.class));
            }
        });
    }
}
