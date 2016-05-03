package com.example.kevin.hci;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarElementFragment extends Fragment {

    private int color;
    private String name ;
    private ListView listView;
    private String[] car_element_rows;
    private int image;
    private View v;

    public CarElementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_car_element, container, false);


        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
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

                    OptionsFragment fragment2 = new OptionsFragment();

                    ((CarElementActivity)getActivity()).changeFragment(fragment2);


                }
            });
        }
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //setColor(bundle.getInt("setColor"));
            //setName(bundle.getString("setName"));
            setCar_element_rows(bundle.getStringArray("setCar_element_rows"));
            //setImage(bundle.getInt("setImage"));
            setListViewMy();
        }

        return v;
    }

    public void setListViewMy(){
        setListView((ListView)v.findViewById(R.id.list_car_elements));
        getListView().setAdapter(new CarElementAdapter(v, getCar_element_rows()));
    }

    public void setColor(int i) {
        color = i;
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)getActivity().findViewById(R.id.toolbar_layout);
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
        //mToolbar.setTitle(name);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(name);
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
        ImageView iv = (ImageView)getActivity().findViewById(R.id.main_imageview_placeholder);
        iv.setImageResource(image);
        iv.setColorFilter(Color.BLACK);
    }

}
