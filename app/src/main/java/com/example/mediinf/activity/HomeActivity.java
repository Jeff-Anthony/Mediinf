package com.example.mediinf.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediinf.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

     private CardView enfermedades, medicamentos, historial, ubicacion;
     private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        enfermedades = findViewById(R.id.card_Enferemedades);
        medicamentos = findViewById(R.id.card_Medicamentos);
        historial = findViewById(R.id.card_Historial);
        ubicacion = findViewById(R.id.card_Farmacias);

        enfermedades.setOnClickListener(this);
        medicamentos.setOnClickListener(this);
        historial.setOnClickListener(this);
        ubicacion.setOnClickListener(this);








    //MENU DEZPLEGABLE IZQUIERDO


    // Setear Toolbar como action bar
    Toolbar toolbar = findViewById(R.id.toolbar);


    // Set DrawerLayout
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);
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
                    home();
                    break;
                case R.id.nav_locations:
                    Toast.makeText(HomeActivity.this, "Go locations...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(HomeActivity.this, "Go settings...", Toast.LENGTH_SHORT).show();
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

    }

    private void home(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    private void callLogout(){
        // Se elimina el estado islogged de la SharePreferences

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("islogged").commit();


        //Finalizamos
        finish();
        //Tambien podemos reedireccionar al loginActivity

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    @Override
    public void onClick(View v) {
        Intent i;

            switch (v.getId()){

                case R.id.card_Enferemedades : i = new Intent(this, EnfermedadesActivity.class);startActivity(i); break;
                case R.id.card_Medicamentos : i = new Intent(this, MediActivity.class);startActivity(i); break;
                case R.id.card_Farmacias: i = new Intent(this, UbicacionMap.class);startActivity(i); break;
                default:break;

            }
    }


}
