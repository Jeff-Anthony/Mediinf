package com.example.mediinf.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediinf.R;
import com.example.mediinf.adapters.MedicamentosAdapter_nat;
import com.example.mediinf.repositories.MedicamentosRepository_nat;

/**
 * A simple {@link Fragment} subclass.
 */
public class medicinas_naturales extends Fragment {


    public medicinas_naturales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView mediList;

        View view = inflater.inflate(R.layout.fragment_medicinas_naturales, container, false);

        mediList = view.findViewById(R.id.RecycleView);
        mediList.setLayoutManager(new LinearLayoutManager(getContext()));

        MedicamentosAdapter_nat adapter = new MedicamentosAdapter_nat();
        adapter.setMedicamentos(MedicamentosRepository_nat.getMedicamentos());
        mediList.setAdapter(adapter);

        return view;

       }

}
