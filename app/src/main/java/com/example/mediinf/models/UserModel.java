package com.example.mediinf.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mediinf.R;
import com.example.mediinf.activity.HomeActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

    ProgressDialog progreso;
    Context context;

    public UserModel (Context context, ProgressDialog progreso){

        this.context=context;
        this.progreso=progreso;

    }

    public void IniciarSesion(final EditText Correo, final EditText Clave){

        progreso = new ProgressDialog(context);
        progreso.setMessage("Cargando..");
        progreso.show();

        String url = "http://"+context.getString(R.string.ip_servidor)+ "/api/login";

        Map<String,String> parametros = new HashMap();
        parametros.put("correo", Correo.getText().toString());
        parametros.put("clave", Clave.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                User usuarios = new User();
                try {
                    if (response.optString("correo").equals("No Existe")) {

                        if(!response.optString("correo").equals(Correo.getText().toString()) && response.optString("clave").equals(Clave.getText().toString())){

                            usuarios.setId(response.optInt("id"));
                            usuarios.setNombres(response.optString("nombre"));
                            usuarios.setApellidos(response.optString("apellido"));
                            usuarios.setDni(response.optString("dni"));
                            usuarios.setCorreo(response.optString("correo"));
                            usuarios.setAlergia(response.optString("alergia"));


                            Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);

                            Toast.makeText(context, "Bienvenido !!! " + usuarios.getApellidos() + usuarios.getNombres(), Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(context, " Correo y/o Password Invalido " + usuarios.getApellidos() + usuarios.getNombres(), Toast.LENGTH_SHORT).show();

                        }



                    } else {

                        Toast.makeText(context, " Correo y/o Password Invalido", Toast.LENGTH_SHORT).show();

                    }

                    progreso.hide();

                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        PatronSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
