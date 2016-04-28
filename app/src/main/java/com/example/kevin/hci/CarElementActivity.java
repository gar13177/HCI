package com.example.kevin.hci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class CarElementActivity extends AppCompatActivity {



    private int color;
    private String name ;
    private ListView listView;
    private String[] car_element_rows;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Prueba");

        ImageView iv = (ImageView)findViewById(R.id.main_imageview_placeholder);
        iv.setImageResource(R.drawable.brake_system);
        iv.setColorFilter(Color.BLACK);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.getBackgroundTintList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //setColor(getColor());
                //setColor(getColor() + 1);
                //if (getColor() > 2) setColor(0);
                //Intent myIntent = new Intent(CarElementActivity.this, OptionsActivity.class);
                //CarElementActivity.this.startActivity(myIntent);
                CarElementActivity.this.finish();

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Intent myIntent = getIntent();
            setColor(myIntent.getIntExtra("setColor", -1));
            setName(myIntent.getStringExtra("setName"));
            setCar_element_rows(myIntent.getStringArrayExtra("setCar_element_rows"));
            setListViewMy();

        }

    }

    public void setListViewMy(){
        setListView((ListView)findViewById(R.id.list_car_elements));
        getListView().setAdapter(new CarElementAdapter(this, getCar_element_rows()));
    }

    public void setColor(int i){
        color = i;
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        //AppBarLayout fl = (AppBarLayout)findViewById(R.id.app_bar);



        if (i == 0){//rojo
            ctl.setContentScrimColor(Color.RED);
            ctl.setBackgroundColor(Color.RED);
            ctl.setStatusBarScrimColor(Color.RED);
            //fl.setBackgroundColor(Color.RED);

        }else if (i == 1){//naranja
            ctl.setContentScrimColor(Color.parseColor("#FFE57219"));
            ctl.setBackgroundColor(Color.parseColor("#FFE57219"));
            ctl.setStatusBarScrimColor(Color.parseColor("#FFE57219"));
            //fl.setBackgroundColor(Color.parseColor("#FFE57219"));

        }else{
            ctl.setContentScrimColor(Color.parseColor("#FF1D8DCE"));
            ctl.setBackgroundColor(Color.parseColor("#FF1D8DCE"));
            ctl.setStatusBarScrimColor(Color.parseColor("#FF1D8DCE"));
            //fl.setBackgroundColor(Color.parseColor("#FF1D8DCE"));
        }


    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        getSupportActionBar().setTitle(name);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String[] getCar_element_rows() {
        return car_element_rows;
    }

    public void setCar_element_rows(String[] car_element_rows) {
        this.car_element_rows = car_element_rows;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
        ImageView iv = (ImageView)findViewById(R.id.main_imageview_placeholder);
        iv.setImageResource(image);
        iv.setColorFilter(Color.BLACK);
    }
}
