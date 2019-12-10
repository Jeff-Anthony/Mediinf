package com.example.mediinf.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.activity.EnfermedadesActivity;
import com.example.mediinf.adapters.MedicamentosAdapter_gen;
import com.example.mediinf.models.ApiError;
import com.example.mediinf.models.Medicamentos_gen;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class medicinas_generales extends Fragment {

    private static final String TAG = EnfermedadesActivity.class.getSimpleName();

    private RecyclerView generales_list;


    public medicinas_generales() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_medicinas_generales, container, false);

        generales_list = view.findViewById(R.id.RecycleView_gen);
        generales_list.setLayoutManager(new LinearLayoutManager(getContext()));
        generales_list.setAdapter(new MedicamentosAdapter_gen());


        showMedicamentos();

        return view;



    }


    public void  showMedicamentos(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        service.findTodo().enqueue(new Callback<List<Medicamentos_gen>>() {
            @Override
            public void onResponse(Call<List<Medicamentos_gen>> call, Response<List<Medicamentos_gen>> response) {
                if (response.isSuccessful()){

                    List<Medicamentos_gen> medicamentos_gen = response.body();
                    Log.d(TAG, "medicamentos Generales" + medicamentos_gen);

                    MedicamentosAdapter_gen adapter = (MedicamentosAdapter_gen) generales_list.getAdapter();
                    adapter.setMedicamentos(medicamentos_gen);
                    adapter.notifyDataSetChanged();



                }else{
                    ApiError error = ApiGenerator.parseError(response);


                }
            }

            @Override
            public void onFailure(Call<List<Medicamentos_gen>> call, Throwable t) {

            }
        });


    }


}
