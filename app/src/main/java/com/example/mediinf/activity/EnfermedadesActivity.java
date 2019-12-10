package com.example.mediinf.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.adapters.EnfermedadesAdapter;
import com.example.mediinf.models.ApiError;
import com.example.mediinf.models.Enfermedades;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnfermedadesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = EnfermedadesActivity.class.getSimpleName();

        ArrayList<Enfermedades> listadoEnfermedades;
       private RecyclerView enfermedadeslist;
       EnfermedadesAdapter enfermedadesAdapter;
        private SearchView buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades);


       // testRest();



        enfermedadeslist = findViewById(R.id.RecycleView_enf);
        enfermedadeslist.setLayoutManager(new LinearLayoutManager(this));
        enfermedadeslist.setAdapter(new EnfermedadesAdapter());

        buscar = findViewById(R.id.buscador);


        showenfermedades();

    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem item = menu.findItem(R.id.buscador);
        SearchView searchView= (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                enfermedadesAdapter.setFilter(listadoEnfermedades);
                return true;
            }
        });

        return true;

    }


    public void showenfermedades(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        service.findAll().enqueue(new Callback<List<Enfermedades>>() {
            @Override
            public void onResponse(Call<List<Enfermedades>> call, Response<List<Enfermedades>> response) {
                if (response.isSuccessful()){

                    List<Enfermedades> enfermedades = response.body();
                    Log.d(TAG, "enfermedades" + enfermedades);

                    EnfermedadesAdapter adapter = (EnfermedadesAdapter) enfermedadeslist.getAdapter();
                    adapter.setEnfermedades(enfermedades);
                    adapter.notifyDataSetChanged();



                }else{
                    ApiError error = ApiGenerator.parseError(response);
                    Toast.makeText(EnfermedadesActivity.this, error.getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Enfermedades>> call, Throwable t) {

            }
        });

    }

    private void testRest(){

            Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.API_BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

            ApiService service = retrofit.create(ApiService.class);

            Call<List<Enfermedades>> call = service.findAll();

            call.enqueue(new Callback<List<Enfermedades>>() {
                @Override
                public void onResponse(Call<List<Enfermedades>> call, Response<List<Enfermedades>> response) {

                    if (response .isSuccessful()){

                        List<Enfermedades> enfermedadesList = response.body();
                        Log.d("EnfermedadesActivity", "Enfermedades" + enfermedadesList);

                    }else{

                        Toast.makeText(EnfermedadesActivity.this,"Error" + response.code(), Toast.LENGTH_SHORT ).show();

                    }

                }

                @Override
                public void onFailure(Call<List<Enfermedades>> call, Throwable t) {

                    Toast.makeText(EnfermedadesActivity.this,"Error Critico" + t.getMessage(), Toast.LENGTH_SHORT ).show();

                }
            });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        try{

            ArrayList<Enfermedades>enfermedadesfiltada=filter(listadoEnfermedades,newText);
            enfermedadesAdapter.setFilter(enfermedadesfiltada);
        }catch(Exception e){
         e.printStackTrace();
        }

        return false;
    }

    private ArrayList<Enfermedades> filter(ArrayList<Enfermedades> enfermedades, String texto){

        ArrayList<Enfermedades> enfermedadesfiltada=new ArrayList<>();

        try{
            texto=texto.toLowerCase();
            for(Enfermedades enfermedad: enfermedades){

                String enfermedades2= enfermedad.getNombre().toLowerCase();

                if(enfermedades2.contains(texto)){

                    enfermedadesfiltada.add(enfermedad);

                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return enfermedadesfiltada;
    }

}
