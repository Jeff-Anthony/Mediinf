package com.example.mediinf.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mediinf.R;
import com.example.mediinf.models.User;
import com.example.mediinf.repositories.UserRepository;

public class InicioMedi extends AppCompatActivity {

    private TextView CorreoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_medi);




        CorreoText = findViewById(R.id.CorreoText);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("correo", null);

        User user = UserRepository.findByCorreo(username);

        if(user != null){

            CorreoText.setText(user.getNombre());

        }




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
            case R.id.encuesta_item:
                callEncuesta();
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


    private void  callEncuesta(){

        Intent intent = new Intent(this, EncuestaActivity.class);
        startActivity(intent);

    }



}
