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
import com.example.mediinf.activity.InfoNaturalActivity;
import com.example.mediinf.models.Medicamentos_nat;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosAdapter_nat extends RecyclerView.Adapter<MedicamentosAdapter_nat.ViewHolder> {

    private List<Medicamentos_nat> medicamentos;

    public void setMedicamentosnat(List<Medicamentos_nat> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public MedicamentosAdapter_nat() {
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
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_general, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        final Medicamentos_nat medicamentosNat = this.medicamentos.get(i);

        final Context context = viewHolder.itemView.getContext();

        viewHolder.medi_nombre.setText(medicamentosNat.getNombre());
        viewHolder.medi_detalle.setText(medicamentosNat.getDetalle());
        viewHolder.medi_precio.setText(medicamentosNat.getPrecio());

        String url = ApiService.API_BASE_URL + "/api/medicamentos_nat/images/" + medicamentosNat.getImagen();

        ApiGenerator.createPicasso(context).load(url).into(viewHolder.medi_imagen);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoNaturalActivity.class);
                intent.putExtra("ID", medicamentosNat.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.medicamentos.size();
    }
}
