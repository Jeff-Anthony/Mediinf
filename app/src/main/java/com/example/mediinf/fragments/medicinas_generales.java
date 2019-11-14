package com.example.mediinf.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediinf.R;
import com.example.mediinf.adapters.MedicamentosAdapter_gen;
import com.example.mediinf.repositories.MedicamentosRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class medicinas_generales extends Fragment {



    public medicinas_generales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       RecyclerView mediList;

        View view = inflater.inflate(R.layout.fragment_medicinas_generales, container, false);

        mediList = view.findViewById(R.id.RecycleView);
        mediList.setLayoutManager(new LinearLayoutManager(getContext()));

        MedicamentosAdapter_gen adapter = new MedicamentosAdapter_gen(getActivity());
        adapter.setMedicamentos(MedicamentosRepository.getMedicamentos());
        mediList.setAdapter(adapter);

        return view;

    }



}
