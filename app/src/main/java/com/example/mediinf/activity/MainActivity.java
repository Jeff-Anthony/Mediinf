package com.example.mediinf.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.models.User;
import com.example.mediinf.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {


    private View Login_Panel;
    private EditText Correo_inicio;
    private EditText Contraseña_inicio;
    private Button buttoninicio;
    private Button buttonregistro;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Panel = findViewById(R.id.Login_Panel);
        Correo_inicio = findViewById(R.id.Corre_inicio);
        Contraseña_inicio = findViewById(R.id.Contraseña_inicio);
        buttonregistro = findViewById(R.id.button_registro);
        buttoninicio = findViewById(R.id.button_ingreso);
        progressBar = findViewById(R.id.ProgresBar);


        buttoninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });



        buttonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showRegistro();
            }
        });



        loadLastUsername();

    }


    @SuppressLint("ShowToast")
    private void showLogin(){

        String correo= Correo_inicio.getText().toString();
        String contraseña=Contraseña_inicio.getText().toString();

        if(correo.isEmpty()){
            Correo_inicio.setError("Ingrese su Correo");
            Toast.makeText(this,"Ingrese su Correo", Toast.LENGTH_SHORT);
            return;
        }
        if(contraseña.isEmpty()){
            Contraseña_inicio.setError("Ingrese su Contaseña");
            Toast.makeText(this, "Ingrese su Contraseña",Toast.LENGTH_SHORT);
            return;
        }

            Login_Panel.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            User usuario = UserRepository.Login(correo,contraseña);

            if(usuario == null ){

                Toast.makeText(this,"Correo y/o cotraseña Invalida", Toast.LENGTH_SHORT).show();
                Login_Panel.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                return;
            }


            // Guardando el estado de tu inicio de sesión en la aplicación
             SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            sp.edit().putBoolean("islogged", true)
                    .putString("correo", correo)
                    .commit();

            startActivity(new Intent(this, InicioMedi.class));
            finish();
    }



    public void showRegistro(){

        Intent intent = new Intent(this, RegistroActivity.class);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            this.getWindow().setExitTransition(new Fade(Fade.OUT));
            this.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, buttonregistro ,"").toBundle());
        }else {


            startActivity(intent);
        }
    }


    private  void  loadLastUsername(){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("correo", null);

        if(username != null){

            Correo_inicio.setText(username);
            Contraseña_inicio.requestFocus();

        }

    }

}
