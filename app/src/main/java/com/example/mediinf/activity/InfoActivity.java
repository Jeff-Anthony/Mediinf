package com.example.mediinf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediinf.R;

public class InfoActivity extends AppCompatActivity {

    TextView titulo, descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ActionBar actionBar = getSupportActionBar();

        titulo = findViewById(R.id.info_nombre);
        descripcion = findViewById(R.id.info_descripcion);
        imagen = findViewById(R.id.info_imagen);

        Intent intent = getIntent();

        String Titulo = intent.getStringExtra("iTitle");
        String Descripcion = intent.getStringExtra("iDesc");

        byte[] bytes = getIntent().getByteArrayExtra("iImage");


        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);

        actionBar.setTitle(Titulo);

        titulo.setText(Titulo);
        descripcion.setText(Descripcion);
        imagen.setImageBitmap(bitmap);

    }
}
