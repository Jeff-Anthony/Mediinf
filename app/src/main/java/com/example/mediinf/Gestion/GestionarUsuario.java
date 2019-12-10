package com.example.mediinf.Gestion;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;

import com.example.mediinf.models.UserModel;

public class GestionarUsuario {

    public Context context;
    public ProgressDialog progreso;
    private UserModel userModel;

    public GestionarUsuario (Context context, ProgressDialog progreso){

        this.context=context;
        this.progreso=progreso;
        userModel = new UserModel(context,progreso);


    }

public  void IniciarSesion(final EditText correo, final EditText password){

        try{

            userModel.IniciarSesion(correo,password);

        }catch(Exception e){

            throw  e;

        }



}


}
