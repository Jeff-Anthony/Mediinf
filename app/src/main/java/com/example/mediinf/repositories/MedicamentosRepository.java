package com.example.mediinf.repositories;

import com.example.mediinf.models.Medicamentos_gen;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosRepository {

    private static List<Medicamentos_gen> medid = new ArrayList<>();
    public static List<Medicamentos_gen> lista_medi(){
        List<Medicamentos_gen> medi = SugarRecord.listAll(Medicamentos_gen.class);
        return medi;
    }




    public static List<Medicamentos_gen> getMedicamentos() {
        return medid;
    }
}
