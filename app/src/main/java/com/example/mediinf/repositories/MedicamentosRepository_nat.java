package com.example.mediinf.repositories;

import com.example.mediinf.models.Medicamentos_nat;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosRepository_nat {

    private static List<Medicamentos_nat> medid = new ArrayList<>();
    public static List<Medicamentos_nat> lista_medi(){
        List<Medicamentos_nat> medi = SugarRecord.listAll(Medicamentos_nat.class);
        return medi;
    }



    public static List<Medicamentos_nat> getMedicamentos() {
        return medid;
    }
}
