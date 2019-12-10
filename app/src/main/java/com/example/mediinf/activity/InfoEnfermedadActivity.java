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
import com.example.mediinf.models.Enfermedades;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoEnfermedadActivity extends AppCompatActivity {

    private static final String TAG = InfoEnfermedadActivity.class.getSimpleName();
    private Long id;
    TextView nombre_enf, descripcion_enf, sintomas_enf,medi_g,medi_l,medi_n;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_enfermedad);

        imagen = findViewById(R.id.info_imagen);
        nombre_enf = findViewById(R.id.enf_nombre_item);
        descripcion_enf = findViewById(R.id.enf_descripcion_item);
        sintomas_enf = findViewById(R.id.enf_sintomas_item);
        medi_g = findViewById(R.id.enf_gen);
        medi_l = findViewById(R.id.enf_lab);
        medi_n = findViewById(R.id.enf_nat);


        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "id:" + id);

        Ejecutar();


    }

    private void Ejecutar(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        Call<Enfermedades> call = service.showEnfermedades(id);

        call.enqueue(new Callback<Enfermedades>() {
            @Override
            public void onResponse(@NonNull Call<Enfermedades> call, @NonNull Response<Enfermedades> response) {
                try {

                    if (response.isSuccessful()) {

                        Enfermedades enfermedades = response.body();
                        Log.d(TAG, "enfermedades: " + enfermedades);

                        nombre_enf.setText(enfermedades.getNombre());
                        descripcion_enf.setText(enfermedades.getDescripcion());
                        sintomas_enf.setText(enfermedades.getSintomas());
                        medi_g.setText(enfermedades.getMedicamento_g());
                        medi_l.setText(enfermedades.getMedicamento_l());
                        medi_n.setText(enfermedades.getMedicamento_n());


                        String url = ApiService.API_BASE_URL + "/api/enfermedad/images/" + enfermedades.getEnfermedad_img();
                        ApiGenerator.createPicasso(InfoEnfermedadActivity.this).load(url).into(imagen);

                    } else {
                        throw new Exception(ApiGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(InfoEnfermedadActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Enfermedades> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(InfoEnfermedadActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }



}
