package com.example.kevin.hci;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Kevin on 25/04/2016.
 */
public class CarElementAdapter extends BaseAdapter {

    String[] car_element_rows;
    Context context;
    static LayoutInflater inflater = null;

    public CarElementAdapter(CarElementActivity mainActivity, String[] car_element_rows){
        this.car_element_rows = car_element_rows;
        context = mainActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return car_element_rows.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder{
        TextView element_name;
        TextView element_value;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_car_element,null);
        holder.element_name = (TextView) rowView.findViewById(R.id.car_element_row_name);
        holder.element_name.setText(car_element_rows[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked "+car_element_rows[position], Toast.LENGTH_LONG).show();

            }
        });

        return rowView;
    }
}
