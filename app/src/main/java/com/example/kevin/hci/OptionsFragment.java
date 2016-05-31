package com.example.kevin.hci;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {

    int color = 2;
    ListView listView;
    int[] images;
    String[] name_rows;
    String[] information_rows;
    CarElementActivity[] activities;

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String name = getResources().getString(R.string.options_menu_name);
        ((CarElementActivity)getActivity()).setName(name);
        ((CarElementActivity)getActivity()).setButtonEnable(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_options, container, false);

        String name = getResources().getString(R.string.options_menu_name);
        ((CarElementActivity)getActivity()).setName(name);
        ((CarElementActivity)getActivity()).setButtonEnable(false);

        name_rows = getResources().getStringArray(R.array.car_elements);
        information_rows = getResources().getStringArray(R.array.car_elements_information);
        TypedArray imgs = getResources().obtainTypedArray(R.array.car_elements_images);
        images = new int[imgs.length()];
        for (int i = 0; i < images.length; i++){
            images[i] = imgs.getResourceId(i,-1);
        }

        /*activities = new CarElementActivity[name_rows.length];
        for (int i = 0; i< activities.length; i++){
            TypedArray evals = getResources().obtainTypedArray(R.array.car_elements_rows);
            CharSequence[] str  = evals.getTextArray(i);
            String[] str2 = new String[str.length];
            for (int j = 0; j<str.length; j++){
                str2[j] = str[j].toString();
            }
            activities[i] = new CarElementActivity();
            activities[i].setColor(0);
            activities[i].setName(name_rows[i]);
            activities[i].setCar_element_rows(str2);
            activities[i].setListViewMy();
        }*/


        listView = (ListView)v.findViewById(R.id.options_menu_list);
        listView.setAdapter(new OptionsAdapter(v, name_rows, information_rows, images));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                TypedArray evals = getResources().obtainTypedArray(R.array.car_elements_rows);
                CharSequence[] str = evals.getTextArray(position);
                String[] str2 = new String[str.length];
                for (int j = 0; j < str.length; j++) {
                    str2[j] = str[j].toString();
                }

                TypedArray evals2 = getResources().obtainTypedArray(R.array.car_elements_values);
                CharSequence[] str3 = evals2.getTextArray(position);
                String[] str4 = new String[str3.length];
                for (int j = 0; j < str3.length; j++) {
                    str4[j] = str3[j].toString();
                }

                CarElementFragment fragment2 = new CarElementFragment();

                Bundle bundle = new Bundle();
                bundle.putString("setName", name_rows[position]);

                bundle.putInt("setColor", 2);
                bundle.putStringArray("setCar_element_rows", str2);
                bundle.putStringArray("setCar_element_values", str4);
                bundle.putInt("setImage", images[position]);

                ((CarElementActivity) getActivity()).setName(name_rows[position]);
                ((CarElementActivity) getActivity()).setActualActivityName("element");
                ((CarElementActivity) getActivity()).setInformation(information_rows[position]);
                ((AppBarLayout)((CarElementActivity) getActivity()).findViewById(R.id.app_bar)).setExpanded(true);

                fragment2.setArguments(bundle);


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, fragment2);
                ft.commit();
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        String name = getResources().getString(R.string.options_menu_name);
        ((CarElementActivity)getActivity()).setName(name);
        ((CarElementActivity)getActivity()).setButtonEnable(false);
        super.onResume();

    }
}
