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
import com.example.mediinf.adapters.MedicamentosAdapter_lab;
import com.example.mediinf.models.ApiError;
import com.example.mediinf.models.Medicamentos_lab;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class medicinas_laboratorio extends Fragment {

    private static final String TAG = EnfermedadesActivity.class.getSimpleName();

    private RecyclerView laboratorio_list;

    public medicinas_laboratorio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicinas_laboratorio, container, false);

        laboratorio_list = view.findViewById(R.id.RecycleView_lab);
        laboratorio_list.setLayoutManager(new LinearLayoutManager(getContext()));
        laboratorio_list.setAdapter(new MedicamentosAdapter_lab());


        showMedicamentos();

        return view;
    }

    public void  showMedicamentos(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        service.findTodoLab().enqueue(new Callback<List<Medicamentos_lab>>() {
            @Override
            public void onResponse(Call<List<Medicamentos_lab>> call, Response<List<Medicamentos_lab>> response) {
                if (response.isSuccessful()){

                    List<Medicamentos_lab> medicamentos_lab = response.body();
                    Log.d(TAG, "medicamentos Laboratorio" + medicamentos_lab);

                    MedicamentosAdapter_lab adapter = (MedicamentosAdapter_lab) laboratorio_list.getAdapter();
                    adapter.setMedicamentoslab(medicamentos_lab);
                    adapter.notifyDataSetChanged();



                }else{
                    ApiError error = ApiGenerator.parseError(response);


                }
            }

            @Override
            public void onFailure(Call<List<Medicamentos_lab>> call, Throwable t) {

            }
        });


    }

}
