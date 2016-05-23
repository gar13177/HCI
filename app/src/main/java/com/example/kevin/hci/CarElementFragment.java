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
    private String[] car_element_values;
    private int image;
    private View v;

    public CarElementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            name = bundle.getString("setName");
            ((CarElementActivity) getActivity()).setName(name);
        }
        ((CarElementActivity)getActivity()).setButtonEnable(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        if (name != null) ((CarElementActivity) getActivity()).setName(name);
        ((CarElementActivity)getActivity()).setButtonEnable(true);
        super.onResume();

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
                    ((CarElementActivity) getActivity()).setActualActivityName("home");
                    OptionsFragment fragment2 = new OptionsFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_container, fragment2);
                    ft.commit();
                }
            });
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //setColor(bundle.getInt("setColor"));
            name = bundle.getString("setName");
            ((CarElementActivity) getActivity()).setName(name);
            setCar_element_rows(bundle.getStringArray("setCar_element_rows"));
            setCar_element_values(bundle.getStringArray("setCar_element_values"));
            ((CarElementActivity)getActivity()).setImage(bundle.getInt("setImage"));
            ((CarElementActivity)getActivity()).setColor(bundle.getInt("setColor"));
            setListViewMy();
        }

        ((CarElementActivity)getActivity()).setButtonEnable(true);

        return v;
    }

    public void setListViewMy(){
        setListView((ListView)v.findViewById(R.id.list_car_elements));
        getListView().setAdapter(new CarElementAdapter(v, getCar_element_rows(), getCar_element_values()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String[] getCar_element_values() {
        return car_element_values;
    }

    public void setCar_element_rows(String[] car_element_rows) {
        this.car_element_rows = car_element_rows;
    }

    public void setCar_element_values(String[] car_element_rows) {
        this.car_element_values = car_element_rows;
    }

}
