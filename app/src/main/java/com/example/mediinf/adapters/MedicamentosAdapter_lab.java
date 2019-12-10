package com.example.mediinf.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediinf.R;
import com.example.mediinf.Service.ApiGenerator;
import com.example.mediinf.Service.ApiService;
import com.example.mediinf.activity.InfoLaboratorioActivity;
import com.example.mediinf.models.Medicamentos_lab;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosAdapter_lab extends RecyclerView.Adapter<MedicamentosAdapter_lab.ViewHolder> {

    private List<Medicamentos_lab> medicamentos;

    public void setMedicamentoslab(List<Medicamentos_lab> medicamentos) {

        this.medicamentos = medicamentos;
    }

    public MedicamentosAdapter_lab() {
        this.medicamentos = new ArrayList<>();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView medi_imagen;
        TextView medi_nombre;
        TextView medi_detalle;
        TextView medi_precio;
        ItemClickListener itemClickListener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            medi_imagen = itemView.findViewById(R.id.image_medi);
            medi_nombre = itemView.findViewById(R.id.nombre_medi);
            medi_detalle = itemView.findViewById(R.id.informacion_medi);
            medi_precio = itemView.findViewById(R.id.precio_medi);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            this.itemClickListener.onItemClickListener(v, getLayoutPosition());

        }

        public void setItemClickListener(ItemClickListener ic) {

            itemClickListener = ic;

        }

    }

    @NonNull
    @Override
    public MedicamentosAdapter_lab.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_general, viewGroup, false);
        return new MedicamentosAdapter_lab.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentosAdapter_lab.ViewHolder viewHolder,final int i) {
        final Medicamentos_lab medicamentosLab = this.medicamentos.get(i);

        final Context context = viewHolder.itemView.getContext();

        viewHolder.medi_nombre.setText(medicamentosLab.getNombre());
        viewHolder.medi_detalle.setText(medicamentosLab.getDetalle());
        viewHolder.medi_precio.setText(medicamentosLab.getPrecio());

        String url = ApiService.API_BASE_URL + "/api/medicamentos_lab/images/" + medicamentosLab.getImagen();

        ApiGenerator.createPicasso(context).load(url).into(viewHolder.medi_imagen);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoLaboratorioActivity.class);
                intent.putExtra("ID", medicamentosLab.getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.medicamentos.size();
    }

}