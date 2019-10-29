package com.example.mediinf.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.mediinf.R;
import com.example.mediinf.fragments.medicinas_generales;
import com.example.mediinf.fragments.medicinas_laboratorio;
import com.example.mediinf.fragments.medicinas_naturales;


public class InicioMedi extends AppCompatActivity {


    private Button firstButton;
    private Button secontButton;
    private Button thirdButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_medi);


        firstButton = findViewById(R.id.fragment_1);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfragment1();
            }
        });

        secontButton = findViewById(R.id.fragment_2);

        secontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfragment2();
            }
        });

        thirdButton = findViewById(R.id.fragment_3);

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfragment3();
            }
        });

    }

    //Para porder hacer el Logout se necesita de estos dos Constructores

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout_item:
                callLogout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void callLogout(){
    // Se elimina el estado islogged de la SharePreferences

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("islogged").commit();


    //Finalizamos
        finish();
    //Tambien podemos reedireccionar al loginActivity

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }




    public void showfragment1(){

        FragmentManager frag= getSupportFragmentManager();
        Fragment fragment = new medicinas_generales();
        frag.beginTransaction().replace(R.id.content,fragment).commit();
    }


    public void showfragment2(){

        FragmentManager frag= getSupportFragmentManager();
        Fragment fragment = new medicinas_laboratorio();
        frag.beginTransaction().replace(R.id.content,fragment).commit();
    }


    public void showfragment3(){

        FragmentManager frag= getSupportFragmentManager();
        Fragment fragment = new medicinas_naturales();
        frag.beginTransaction().replace(R.id.content,fragment).commit();
    }



}
