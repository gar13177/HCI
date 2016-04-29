package com.example.kevin.hci;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    int color = 2;
    ListView listView;
    int[] images;
    String[] name_rows;
    String[] information_rows;
    CarElementActivity[] activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        getSupportActionBar().setTitle(R.string.options_menu_name);


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

        listView = (ListView)findViewById(R.id.options_menu_list);
        listView.setAdapter(new OptionsAdapter(this, name_rows, information_rows, images));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TypedArray evals = getResources().obtainTypedArray(R.array.car_elements_rows);
                CharSequence[] str = evals.getTextArray(position);
                String[] str2 = new String[str.length];
                for (int j = 0; j < str.length; j++) {
                    str2[j] = str[j].toString();
                }

                Intent myIntent = new Intent(OptionsActivity.this, CarElementActivity.class);
                myIntent.putExtra("setColor", position % 3);
                myIntent.putExtra("setName", name_rows[position]);
                myIntent.putExtra("setCar_element_rows", str2);
                myIntent.putExtra("setImage", images[position]);


                OptionsActivity.this.startActivity(myIntent);
                //OptionsActivity.this.finish();
            }
        });



    }
}
