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

    static {
        medid.add(new Medicamentos_nat(001,"Gripe","General","Receta de Miel","natural1",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "N/A"));

        medid.add(new Medicamentos_nat(002,"Gripe","General","Infusion de Jengibre","natural2",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta previene futuros resfrios comunes en el dia a dia" +
                        "y genera bienestar en los proximos dias siguientes", "N/A"));

    }


    public static List<Medicamentos_nat> getMedicamentos() {
        return medid;
    }
}
