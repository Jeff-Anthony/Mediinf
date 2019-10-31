package com.example.mediinf.adapters;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http//192.168.1.10/Register.php";
    private Map<String, String> params;
    public RegistroRequest(String nombre, String apellido, String dni, String correo, String alergia, String contraseña, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("nombre", nombre);
        params.put("apellido", apellido);
        params.put("dni",dni);
        params.put("correo", correo);
        params.put("alergia", alergia);
        params.put("contraseña", contraseña);
    }

    public Map<String, String> getParams() {
        return params;
    }
}
