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
import com.example.mediinf.models.Medicamentos_lab;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoLaboratorioActivity extends AppCompatActivity {

    private static final String TAG = InfoLaboratorioActivity.class.getSimpleName();
    private Long id;
    TextView nombre, informacion, precio,tipo;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorio_info);


        nombre = findViewById(R.id.info_nombre_lab);
        informacion = findViewById(R.id.info_descripcion_lab);
        precio = findViewById(R.id.info_precio_lab);
        tipo   = findViewById(R.id.info_tipo_lab);
        imagen = findViewById(R.id.info_imagen_lab);

        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "id:" + id);


        Ejecutar();

    }


    private void Ejecutar(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<Medicamentos_lab> call = service.showMedicamentosLab(id);

        call.enqueue(new Callback<Medicamentos_lab>() {
            @Override
            public void onResponse(@NonNull Call<Medicamentos_lab> call, @NonNull Response<Medicamentos_lab> response) {
                try {

                    if (response.isSuccessful()) {

                        Medicamentos_lab medicamentos_lab = response.body();
                        Log.d(TAG, "Medicamentos_lab: " + medicamentos_lab);

                        nombre.setText(medicamentos_lab.getNombre());
                        informacion.setText(medicamentos_lab.getInformacion());
                        precio.setText(medicamentos_lab.getPrecio());
                        tipo.setText(medicamentos_lab.getTipo());



                        String url = ApiService.API_BASE_URL + "/api/medicamentos_lab/images/" + medicamentos_lab.getImagen();
                        ApiGenerator.createPicasso(InfoLaboratorioActivity.this).load(url).into(imagen);

                    } else {
                        throw new Exception(ApiGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(InfoLaboratorioActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Medicamentos_lab> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(InfoLaboratorioActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }


}
