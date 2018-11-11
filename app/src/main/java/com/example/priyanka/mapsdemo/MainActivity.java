package com.example.priyanka.mapsdemo;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting emojis to Menu
        String[] locationList = {"\uD83D\uDCBC Job Listings",
                "\uD83D\uDDFA Job Search Map", "\uD83D\uDCAC Chat with Recruiters","\uD83C\uDFE5 Clinics",
                "\uD83D\uDC99 Food Bank", "\uD83C\uDFE0 Shelters",
                "\uD83D\uDCB5 Interview Tips", "❓ FAQ",
                "\uD83D\uDCCD Offline Maps"};
        ListAdapter LocationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,locationList);
        ListView LocationView = (ListView) findViewById(R.id.locations);
        LocationView.setAdapter(LocationAdapter);

        LocationView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String locationList = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, locationList, Toast.LENGTH_SHORT).show();

                        //Goes to Job listing
                        if (position == 0)
                        {
                            Intent page = new Intent(MainActivity.this, JobListings.class);
                            startActivity(page);
                        }

                        //goes to google maps api
                        if (position == 1)
                        {
                            Intent dash  = new Intent(MainActivity.this, MapsActivity.class);
                            startActivity(dash);
                        }

                        //goes to dashboard
                        if (position == 2)
                        {
                            Intent dash  = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(dash);
                        }

                        //goes to dashboard if else
                        if (position > 2)
                        {
                            Intent dash  = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(dash);
                        }
                    }
                }

        );



    }
}
