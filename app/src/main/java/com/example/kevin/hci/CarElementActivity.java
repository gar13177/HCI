package com.example.kevin.hci;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

public class CarElementActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener{


    private int color;
    private String name ;
    private ListView listView;
    private String[] car_element_rows;
    private int image;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_car_element);
        setContentView(R.layout.activity_drawer);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
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


                }
            });
        }

        OptionsFragment f1 = new OptionsFragment();
        changeFragment(f1);




        /*getSupportActionBar().setTitle("Prueba");

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
            setImage(myIntent.getIntExtra("setImage",-1));
            setListViewMy();

        }*/


    }

    public void changeFragment(Fragment f1){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        Bundle bundle = f1.getArguments();
        if (bundle != null) {

            setColor(bundle.getInt("setColor"));
            setName(bundle.getString("setName"));
            setImage(bundle.getInt("setImage"));

        }else if (f1 instanceof OptionsFragment){
            fab.setVisibility(View.GONE);
            setColor(2);

        }



        ft.replace(R.id.main_container, f1);
        ft.commit();

    }

    public void setColor(int i) {
        color = i;
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        //AppBarLayout fl = (AppBarLayout)findViewById(R.id.app_bar);



        if (i == 0){//rojo
            ctl.setContentScrimColor(getResources().getColor(R.color.colorEmergency));
            ctl.setBackgroundColor(getResources().getColor(R.color.colorEmergency));
            ctl.setStatusBarScrimColor(getResources().getColor(R.color.colorEmergency));
            //fl.setBackgroundColor(Color.RED);

        }else if (i == 1){//naranja
            ctl.setContentScrimColor(getResources().getColor(R.color.colorWarning));
            ctl.setBackgroundColor(getResources().getColor(R.color.colorWarning));
            ctl.setStatusBarScrimColor(getResources().getColor(R.color.colorWarning));
            //fl.setBackgroundColor(Color.parseColor("#FFE57219"));

        }else{
            ctl.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
            ctl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            ctl.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimary));
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
        //mToolbar.setTitle(name);
        getSupportActionBar().setTitle(name);
    }

    public void setImage(int image) {
        this.image = image;
        ImageView iv = (ImageView)findViewById(R.id.main_imageview_placeholder);
        iv.setImageResource(image);
        iv.setColorFilter(Color.BLACK);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_element, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            Intent myIntent = new Intent(CarElementActivity.this, ProfileActivity.class);
            CarElementActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
