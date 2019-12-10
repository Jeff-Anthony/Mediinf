package com.example.mediinf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mediinf.Gestion.GestionarUsuario;
import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.models.ApiError;
import com.example.mediinf.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();


    private View Login_Panel;
    private EditText Correo_inicio;
    private EditText Contraseña_inicio;
    private Button buttoninicio;
    private Button buttonregistro;
    private ProgressBar progressBar;
    ProgressDialog progres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Login_Panel = findViewById(R.id.Login_Panel);

        Correo_inicio = findViewById(R.id.Corre_inicio);
        Contraseña_inicio = findViewById(R.id.Contraseña_inicio);

        buttonregistro = findViewById(R.id.button_registro);
        buttoninicio = findViewById(R.id.button_ingreso);

        progressBar = findViewById(R.id.ProgresBar);








        buttoninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showinicio();

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


    private void showinicio(){

        String correo= Correo_inicio.getText().toString();
        final String clave=Contraseña_inicio.getText().toString();

        if(correo.isEmpty()){
            Correo_inicio.setError("Ingrese su Correo");
            Toast.makeText(this,"Ingrese su Correo", Toast.LENGTH_SHORT);
            return;
        }
        if(clave.isEmpty()){
            Contraseña_inicio.setError("Ingrese su Contaseña");
            Toast.makeText(this, "Ingrese su Contraseña",Toast.LENGTH_SHORT);
            return;
        }

            Login_Panel.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<User> call = service.login(correo,clave);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()){

                    User user = response.body();
                    Log.d(TAG, "user" + user);

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    sp.edit()
                            .putString("correo", user.getCorreo())
                            .putBoolean("islogged", true)
                            .commit();

                    Toast.makeText(LoginActivity.this, "Bienvenido " + user.getNombres(), Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();

                }else{

                    ApiError error = ApiGenerator.parseError(response);
                    Toast.makeText(LoginActivity.this, "onError:" + error.getMessage(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "onFailure: " + t.toString(), Toast.LENGTH_LONG).show();

            }
        });


        // Guardando el estado de tu inicio de sesión en la aplicación
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putBoolean("islogged", true)
                .putString("correo", correo)
                .commit();

            startActivity(new Intent(this, HomeActivity.class));
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_ingreso:
                GestionarUsuario gestionarUsuario = new GestionarUsuario(this, progres);
                gestionarUsuario.IniciarSesion(Correo_inicio,Contraseña_inicio);
            break;
        }

    }
}
