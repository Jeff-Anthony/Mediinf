package com.example.mediinf.repositories;

import com.example.mediinf.models.Medicamentos_lab;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosRepository_lab {

    private static List<Medicamentos_lab> medid = new ArrayList<>();
    public static List<Medicamentos_lab> lista_medi(){
        List<Medicamentos_lab> medi = SugarRecord.listAll(Medicamentos_lab.class);
        return medi;
    }



    public static List<Medicamentos_lab> getMedicamentos() {
        return medid;
    }
}
