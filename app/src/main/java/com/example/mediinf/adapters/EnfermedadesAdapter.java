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
import com.example.mediinf.activity.InfoEnfermedadActivity;
import com.example.mediinf.models.Enfermedades;

import java.util.ArrayList;
import java.util.List;

public class EnfermedadesAdapter extends RecyclerView.Adapter<EnfermedadesAdapter.ViewHolder> {


    private List<Enfermedades> enfermedades;

    public EnfermedadesAdapter(){
        this.enfermedades = new ArrayList<>();
    }



    public void setEnfermedades(List<Enfermedades> enfermedades){
        this.enfermedades = enfermedades;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imagen;
        TextView nombre;
        TextView tipo;
        ItemClickListener itemClickListener;



        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.foto_image);
            nombre = itemView.findViewById(R.id.nombre_enfermedad);
            tipo = itemView.findViewById(R.id.tipo_text);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            this.itemClickListener.onItemClickListener(v,getLayoutPosition());

        }

        public void setItemClickListener(ItemClickListener ic){

            itemClickListener = ic;

        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enfermedad, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
      final  Enfermedades enfermedadess = this.enfermedades.get(i);

        final Context context = viewHolder.itemView.getContext();

        viewHolder.nombre.setText(enfermedadess.getNombre());
        viewHolder.tipo.setText("Tipo:  " + enfermedadess.getApto());

        String url = ApiService.API_BASE_URL + "/api/enfermedad/images/" + enfermedadess.getEnfermedad_img();

        ApiGenerator.createPicasso(context).load(url).into(viewHolder.imagen);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoEnfermedadActivity.class);
                intent.putExtra("ID", enfermedadess.getId());
                context.startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount() {
        return this.enfermedades.size();


    }


    public void setFilter(ArrayList<Enfermedades> enfermedades){

        this.enfermedades=new ArrayList<>();
        this.enfermedades.addAll(enfermedades);
        notifyDataSetChanged();


    }


}
