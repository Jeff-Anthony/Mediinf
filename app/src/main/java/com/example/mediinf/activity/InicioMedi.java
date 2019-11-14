package com.example.mediinf.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.fragments.medicinas_generales;
import com.example.mediinf.fragments.medicinas_laboratorio;
import com.example.mediinf.fragments.medicinas_naturales;


public class InicioMedi extends AppCompatActivity{



    private Button firstButton;
    private Button secontButton;
    private Button thirdButton;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_medi);




        //MENU DEZPLEGABLE IZQUIERDO


        // Setear Toolbar como action bar
        Toolbar toolbar = findViewById(R.id.toolbar);


        // Set DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Set drawer toggle icon
        //final ActionBar ab = getSupportActionBar();
        //if (ab != null) {
        //    ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        //    ab.setDisplayHomeAsUpEnabled(true);
        //}

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // Set NavigationItemSelectedListener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        // Change navigation header information
        ImageView photoImage = (ImageView)
                navigationView.getHeaderView(0).findViewById(R.id.menu_photo);
        photoImage.setBackgroundResource(R.drawable.ic_profile);
        TextView fullnameText = (TextView)
                navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
        fullnameText.setText("Llanos Jeff");
        TextView emailText = (TextView)
                navigationView.getHeaderView(0).findViewById(R.id.menu_email);
        emailText.setText("jeff@tecsup.edu.pe");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Do action by menu item id
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(InicioMedi.this, "Go home...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_locations:
                        Toast.makeText(InicioMedi.this, "Go locations...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(InicioMedi.this, "Go settings...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                                  callLogout();
                                break;

                }
                // Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


// ***********************

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                else
                    drawerLayout.closeDrawer(GravityCompat.START);
                // Close drawer
                return true;
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
