package com.example.mediinf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity{

    private static final String TAG = RegistroActivity.class.getSimpleName();

   private  EditText nombre, apellido, dni, correo, alergia, contraseña, edad;

   private Button buttonregresar, buttonregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombre_registro);
        apellido = findViewById(R.id.apellido_registro);
        dni = findViewById(R.id.dni_registro);
        edad = findViewById(R.id.edad_registro);
        correo = findViewById(R.id.correo_registro);
        alergia = findViewById(R.id.alergia_registro);
        contraseña = findViewById(R.id.contraseña_registro);

        buttonregistro = findViewById(R.id.button_usuario_registro);

        buttonregresar = findViewById(R.id.button_regreso);
        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegreso();
            }
        });

    }

    public void showRegreso(){

        Intent intent = new Intent(this, LoginActivity.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            this.getWindow().setExitTransition(new Fade(Fade.OUT));
            this.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, buttonregresar ,"").toBundle());
        }else {


            startActivity(intent);
        }
    }

    private Bitmap bitmap;

    public void RegistroUsuario(View view) {

        String nombre_usu = nombre.getText().toString();
        String apellido_usu = apellido.getText().toString();
        String dni_usu = dni.getText().toString();
        String edad_usu = edad.getText().toString();
        String correo_usu = correo.getText().toString();
        String alergia_usu = alergia.getText().toString();
        String clave_usu = contraseña.getText().toString();

        if(nombre_usu.isEmpty() || apellido_usu.isEmpty() || dni_usu.isEmpty() || edad_usu.isEmpty() || correo_usu.isEmpty() || alergia_usu.isEmpty() || clave_usu.isEmpty()){

            Toast.makeText(this,"Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
            return;
        }


        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<User> call;

        if(bitmap == null) {

            call = service.createUsuario(nombre_usu,apellido_usu,dni_usu,edad_usu,correo_usu,alergia_usu,clave_usu);

        }else{
            RequestBody nombrePart = RequestBody.create(MultipartBody.FORM, nombre_usu);
            RequestBody apellidoPart = RequestBody.create(MultipartBody.FORM, apellido_usu);
            RequestBody dniPart = RequestBody.create(MultipartBody.FORM, dni_usu);
            RequestBody edadPart = RequestBody.create(MultipartBody.FORM, edad_usu);
            RequestBody correoPart = RequestBody.create(MultipartBody.FORM, correo_usu);
            RequestBody alergiaPart = RequestBody.create(MultipartBody.FORM, alergia_usu);
            RequestBody clavePart = RequestBody.create(MultipartBody.FORM, clave_usu);


            call = service.createUsuario(nombrePart,apellidoPart,dniPart,edadPart,correoPart,alergiaPart,clavePart);

        }


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                try{

                    if(response.isSuccessful()){

                        User usuarios = response.body();
                        Log.d(TAG , "Usuario: " + usuarios);

                        Toast.makeText(RegistroActivity.this, "Registro Realizado Correctamente", Toast.LENGTH_SHORT).show();

                        setResult(RESULT_OK);

                        finish();
                    }else{
                        throw new Exception(ApiGenerator.parseError(response).getMessage());
                    }


                }catch(Throwable t){
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(RegistroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(RegistroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}
