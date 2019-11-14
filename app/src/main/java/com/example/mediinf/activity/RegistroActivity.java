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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediinf.R;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity{

    EditText nombre, apellido, dni, correo, alergia, contraseña;

    Button buttonregresar, buttonregistro;

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

        buttonregistro = findViewById(R.id.button_usuario_registro);
        buttonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistro("http://192.168.1.14:80/Mediinft/Register.php");
            }
        });


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



    public void showRegistro(String URL){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("apellido",apellido.getText().toString());
                parametros.put("dni",dni.getText().toString());
                parametros.put("correo",correo.getText().toString());
                parametros.put("alergia",alergia.getText().toString());
                parametros.put("passwords",contraseña.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
