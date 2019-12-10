package com.example.mediinf.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.models.Medicamentos_gen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoGenericoActivity extends AppCompatActivity {

    private static final String TAG = InfoEnfermedadActivity.class.getSimpleName();
    private Long id;
    TextView nombre, informacion, precio,tipo;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generico_info);


        nombre = findViewById(R.id.info_nombre);
        informacion = findViewById(R.id.info_descripcion);
        precio = findViewById(R.id.info_precio);
        tipo   = findViewById(R.id.info_tipo);
        imagen = findViewById(R.id.info_imagen);

        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "id:" + id);


        Ejecutar();

    }


    private void Ejecutar(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<Medicamentos_gen> call = service.showMedicamentos(id);

        call.enqueue(new Callback<Medicamentos_gen>() {
            @Override
            public void onResponse(@NonNull Call<Medicamentos_gen> call, @NonNull Response<Medicamentos_gen> response) {
                try {

                    if (response.isSuccessful()) {

                        Medicamentos_gen medicamentos_gen = response.body();
                        Log.d(TAG, "Medicamentos_gen: " + medicamentos_gen);

                        nombre.setText(medicamentos_gen.getNombre());
                        informacion.setText(medicamentos_gen.getInformacion());
                        precio.setText(medicamentos_gen.getPrecio());
                        tipo.setText(medicamentos_gen.getTipo());



                        String url = ApiService.API_BASE_URL + "/api/medicamentos_gen/images/" + medicamentos_gen.getImagen();
                        ApiGenerator.createPicasso(InfoGenericoActivity.this).load(url).into(imagen);

                    } else {
                        throw new Exception(ApiGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(InfoGenericoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Medicamentos_gen> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(InfoGenericoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }


}
