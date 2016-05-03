package com.example.kevin.hci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ImageButton buttonLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Button b1=(Button)findViewById(R.id.guardar);
        EditText name,aseguradora,emergencia,modelo;
        name = (EditText)findViewById(R.id.name);
        name.setText(sharedpreferences.getString("nameKey", ""));
        aseguradora = (EditText)findViewById(R.id.aseguradora);
        aseguradora.setText(sharedpreferences.getString("aseguradoraKey", ""));
        emergencia = (EditText)findViewById(R.id.emergecia);
        emergencia.setText(sharedpreferences.getString("emergenciaKey", ""));
        modelo = (EditText)findViewById(R.id.modelo);
        modelo.setText(sharedpreferences.getString("modeloKey", ""));


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name, aseguradora, emergencia, modelo;
                name = (EditText) findViewById(R.id.name);
                aseguradora = (EditText) findViewById(R.id.aseguradora);
                emergencia = (EditText) findViewById(R.id.emergecia);
                modelo = (EditText) findViewById(R.id.modelo);
                String nameF = name.getText().toString();
                String aseguradoraF = aseguradora.getText().toString();
                String emergenciaF = emergencia.getText().toString();
                String modeloF = modelo.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("nameKey", nameF);
                editor.putString("aseguradoraKey", aseguradoraF);
                editor.putString("emergenciaKey", emergenciaF);
                editor.putString("modeloKey", modeloF);
                editor.commit();
                Toast.makeText(ProfileActivity.this, "guardado", Toast.LENGTH_LONG).show();
            }
        });
        buttonLoadImage = (ImageButton)findViewById(R.id.foto);
        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            //textTargetUri.setText(targetUri.toString());
            ImageButton targetImage = (ImageButton)findViewById(R.id.foto);
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

