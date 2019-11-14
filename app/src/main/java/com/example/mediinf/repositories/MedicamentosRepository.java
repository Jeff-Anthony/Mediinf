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

    static {
        medid.add(new Medicamentos_gen(001,"Gripe","General","Nastizol","gripe1",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/1.00"));

        medid.add(new Medicamentos_gen(002,"Gripe","General","GelocatilGripe","gripe2",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta previene futuros resfrios comunes en el dia a dia" +
                        "y genera bienestar en los proximos dias siguientes", "S/1.50"));

        medid.add(new Medicamentos_gen(003,"Gripe","General","Tylenol","gripe3",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/2.00"));

        medid.add(new Medicamentos_gen(004,"Gripe","General","GripaVick","gripe4",
                "Es una pastilla para la gripe que ayuda a aliviar el dolor de la garganta y mucosidad en la garganta", "S/1.20"));

    }


    public static List<Medicamentos_gen> getMedicamentos() {
        return medid;
    }
}
