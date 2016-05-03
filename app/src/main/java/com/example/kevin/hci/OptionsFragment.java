package com.example.kevin.hci;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_options, container, false);



        //((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(R.string.options_menu_name);

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

                CarElementFragment fragment2 = new CarElementFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("setColor", position % 3);
                bundle.putString("setName", name_rows[position]);
                bundle.putStringArray("setCar_element_rows", str2);
                bundle.putInt("setImage", images[position]);

                fragment2.setArguments(bundle);

                ((CarElementActivity)getActivity()).changeFragment(fragment2);

            }
        });


        return v;
    }

}
