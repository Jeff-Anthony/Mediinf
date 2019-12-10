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
import com.example.mediinf.adapters.MedicamentosAdapter_nat;
import com.example.mediinf.models.ApiError;
import com.example.mediinf.models.Medicamentos_nat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class medicinas_naturales extends Fragment {

    private static final String TAG = EnfermedadesActivity.class.getSimpleName();

    private RecyclerView natural_list;


    public medicinas_naturales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicinas_naturales, container, false);

        natural_list = view.findViewById(R.id.RecycleView_nat);
        natural_list.setLayoutManager(new LinearLayoutManager(getContext()));
        natural_list.setAdapter(new MedicamentosAdapter_nat());


        showMedicamentos();

        return view;

       }


    public void  showMedicamentos(){

        ApiService service = ApiGenerator.createService(ApiService.class);

        service.findTodoNat().enqueue(new Callback<List<Medicamentos_nat>>() {
            @Override
            public void onResponse(Call<List<Medicamentos_nat>> call, Response<List<Medicamentos_nat>> response) {
                if (response.isSuccessful()){

                    List<Medicamentos_nat> medicamentos_nat = response.body();
                    Log.d(TAG, "medicamentos Naturales" + medicamentos_nat);

                    MedicamentosAdapter_nat adapter = (MedicamentosAdapter_nat) natural_list.getAdapter();
                    adapter.setMedicamentosnat(medicamentos_nat);
                    adapter.notifyDataSetChanged();



                }else{
                    ApiError error = ApiGenerator.parseError(response);


                }
            }

            @Override
            public void onFailure(Call<List<Medicamentos_nat>> call, Throwable t) {

            }
        });


    }


}
