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

    static {
        medid.add(new Medicamentos_lab(001,"Gripe","Laboratorio","Rapivab","rapivab1",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/4.00"));

        medid.add(new Medicamentos_lab(002,"Gripe","General","Bisolvon","bisolvon",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta previene futuros resfrios comunes en el dia a dia" +
                        "y genera bienestar en los proximos dias siguientes", "S/1.50"));

        medid.add(new Medicamentos_lab(003,"Gripe","General","Tylenol","gripe3",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/2.00"));

        medid.add(new Medicamentos_lab(004,"Gripe","General","GripaVick","gripe4",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/1.20"));

    }


    public static List<Medicamentos_lab> getMedicamentos() {
        return medid;
    }
}
