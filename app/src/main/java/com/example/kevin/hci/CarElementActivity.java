package com.example.kevin.hci;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class CarElementActivity extends AppCompatActivity {

    CarElementAdapter cea;
    ListView listView;

    String[] car_elements;
    String[] car_element_rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.getBackgroundTintList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //car_elements = getResources().getStringArray(R.array.car_elements);
        car_element_rows = getResources().getStringArray(R.array.element_1_rows);
        listView = (ListView)findViewById(R.id.list_car_elements);
        listView.setAdapter(new CarElementAdapter(this,car_element_rows));

    }
}
