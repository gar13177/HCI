package com.example.kevin.hci;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin on 27/04/2016.
 */
public class OptionsAdapter extends BaseAdapter {

    String[] options_rows;
    String[] information_rows;
    int[] image_resource;
    Context context;
    static LayoutInflater inflater = null;

    public OptionsAdapter(View mainActivity, String[] options_rows, String[] information_rows, int[] image_resource){
        this.options_rows = options_rows;
        this.information_rows = information_rows;
        this.image_resource = image_resource;



        context = mainActivity.getContext();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return options_rows.length;
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
        ImageView image;
        TextView option;
        TextView information;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_options_menu,null);
        holder.option = (TextView) rowView.findViewById(R.id.option_menu_name);
        holder.option.setText(options_rows[position]);

        holder.image = (ImageView) rowView.findViewById(R.id.option_menu_image);
        holder.image.setImageResource(image_resource[position]);
        holder.image.setColorFilter(Color.BLACK);

        holder.information = (TextView) rowView.findViewById(R.id.option_menu_information);
        holder.information.setText(information_rows[position]);




        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You Clicked " + options_rows[position], Toast.LENGTH_LONG).show();


            }
        });*/


        return rowView;
    }
}
