package com.example.mediinf.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.repositories.UserRepository;

public class RegistroActivity extends AppCompatActivity{

    EditText nombre, apellido, dni, correo, alergia, contraseña;

    private Button buttonregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombre_registro);
        apellido = findViewById(R.id.apellido_registro);
        dni = findViewById(R.id.dni_registro);
        correo = findViewById(R.id.correo_registro);
        alergia = findViewById(R.id.alergia_registro);
        contraseña = findViewById(R.id.contraseña_registro);



        buttonregresar = findViewById(R.id.button_regreso);
        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegreso();
            }
        });

    }

    public void showRegreso(){

        Intent intent = new Intent(this, MainActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            this.getWindow().setExitTransition(new Fade(Fade.OUT));
            this.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, buttonregresar ,"").toBundle());
        }else {


            startActivity(intent);
        }
    }



    public void callRegister(View view){
        final String name = nombre.getText().toString();
        final String ape = apellido.getText().toString();
        final String DNI = dni.getText().toString();
        final String corr = correo.getText().toString();
        final String ale = alergia.getText().toString();
        final String con = contraseña.getText().toString();

        if(name.isEmpty() || ape.isEmpty() || DNI.isEmpty()|| corr.isEmpty()|| ale.isEmpty()|| con.isEmpty()){
            Toast.makeText(this, "Completa los Campos faltantes", Toast.LENGTH_SHORT).show();
            return;
        }else{

            Toast.makeText(this, "Registro realizado con Exito !!", Toast.LENGTH_SHORT).show();

        }

        UserRepository.create(name, ape,DNI, corr, ale, con);

        finish();

    }

}
