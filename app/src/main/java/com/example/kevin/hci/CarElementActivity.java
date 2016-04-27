package com.example.kevin.hci;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

public class CarElementActivity extends AppCompatActivity {

    int color = 2;

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
        getSupportActionBar().setTitle("Prueba");

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.getBackgroundTintList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setColor(color);
                color++;
                if (color>2) color = 0;
            }
        });

        //car_elements = getResources().getStringArray(R.array.car_elements);
        car_element_rows = getResources().getStringArray(R.array.element_1_rows);
        listView = (ListView)findViewById(R.id.list_car_elements);
        listView.setAdapter(new CarElementAdapter(this,car_element_rows));

    }

    public void setColor(int i){
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        AppBarLayout fl = (AppBarLayout)findViewById(R.id.app_bar);


        if (i == 0){//rojo
            ctl.setContentScrimColor(Color.RED);
            fl.setBackgroundColor(Color.RED);

        }else if (i == 1){//naranja
            ctl.setContentScrimColor(Color.parseColor("#FFE57219"));
            fl.setBackgroundColor(Color.parseColor("#FFE57219"));

        }else{
            ctl.setContentScrimColor(Color.parseColor("#FF1D8DCE"));
            fl.setBackgroundColor(Color.parseColor("#FF1D8DCE"));
        }


    }
}
