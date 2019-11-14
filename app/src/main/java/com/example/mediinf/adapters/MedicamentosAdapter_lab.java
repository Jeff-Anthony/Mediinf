package com.example.mediinf.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediinf.R;
import com.example.mediinf.models.Medicamentos_lab;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosAdapter_lab extends RecyclerView.Adapter<MedicamentosAdapter_lab.ViewHolder> {

    private List<Medicamentos_lab> medicamentos;

    public void setMedicamentos(List<Medicamentos_lab> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public MedicamentosAdapter_lab() {
        this.medicamentos = new ArrayList<>();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView medi_imagen;
        TextView medi_nombre;
        TextView medi_informacion;
        TextView medi_precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medi_imagen = itemView.findViewById(R.id.image_medi);
            medi_nombre = itemView.findViewById(R.id.nombre_medi);
            medi_informacion = itemView.findViewById(R.id.informacion_medi);
            medi_precio = itemView.findViewById(R.id.precio_medi);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_general, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        Medicamentos_lab medicamentosGen = this.medicamentos.get(i);

        Context context = viewHolder.itemView.getContext();

        viewHolder.medi_nombre.setText(medicamentosGen.getNombre());
        viewHolder.medi_informacion.setText(medicamentosGen.getInformacion());
        viewHolder.medi_precio.setText(medicamentosGen.getPrecio());
        int idres = context.getResources().getIdentifier(medicamentosGen.getImagen(), "drawable", context.getPackageName());
        viewHolder.medi_imagen.setImageResource(idres);
    }

    @Override
    public int getItemCount() {
        return this.medicamentos.size();
    }
}
