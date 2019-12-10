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
import com.example.mediinf.models.Medicamentos_nat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoNaturalActivity extends AppCompatActivity {

    private static final String TAG = InfoNaturalActivity.class.getSimpleName();
    private Long id;
    TextView nombre, informacion, precio,tipo;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natural_info);


        nombre = findViewById(R.id.info_nombre_nat);
        informacion = findViewById(R.id.info_descripcion_nat);

        tipo   = findViewById(R.id.info_tipo_nat);
        imagen = findViewById(R.id.info_imagen_nat);

        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "id:" + id);


        Ejecutar();

    }


    private void Ejecutar(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<Medicamentos_nat> call = service.showMedicamentosNat(id);

        call.enqueue(new Callback<Medicamentos_nat>() {
            @Override
            public void onResponse(@NonNull Call<Medicamentos_nat> call, @NonNull Response<Medicamentos_nat> response) {
                try {

                    if (response.isSuccessful()) {

                        Medicamentos_nat medicamentos_nat = response.body();
                        Log.d(TAG, "Medicamentos_nat: " + medicamentos_nat);

                        nombre.setText(medicamentos_nat.getNombre());
                        informacion.setText(medicamentos_nat.getInformacion());
                        tipo.setText(medicamentos_nat.getTipo());



                        String url = ApiService.API_BASE_URL + "/api/medicamentos_nat/images/" + medicamentos_nat.getImagen();
                        ApiGenerator.createPicasso(InfoNaturalActivity.this).load(url).into(imagen);

                    } else {
                        throw new Exception(ApiGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(InfoNaturalActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Medicamentos_nat> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(InfoNaturalActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }


}
