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
        String[] locationList = {"\uD83D\uDD25 Find Fire",
                "\uD83D\uDE91\uD83C\uDF1F Medical Aid", "\uD83D\uDE92\uD83C\uDF1F Find Responders",
                "\uD83D\uDC99 Bluetooth Connect", "\uD83C\uDFE5 Shelters",
                "\uD83D\uDCB5 Donate to Relief", "\uD83D\uDCCD FAQ",
                "\uD83D\uDCCD Contact Us"};
        ListAdapter LocationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,locationList);
        ListView LocationView = (ListView) findViewById(R.id.locations);
        LocationView.setAdapter(LocationAdapter);

        LocationView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String locationList = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, locationList, Toast.LENGTH_SHORT).show();

                        Intent page = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(page);
                    }
                }
        );



    }
}
