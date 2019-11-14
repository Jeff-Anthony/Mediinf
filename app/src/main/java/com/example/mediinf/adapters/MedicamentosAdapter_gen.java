package com.example.mediinf.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediinf.R;
import com.example.mediinf.activity.InfoActivity;
import com.example.mediinf.models.Medicamentos_gen;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MedicamentosAdapter_gen extends RecyclerView.Adapter<MedicamentosAdapter_gen.ViewHolder>{

    private final Context c;
    private List<Medicamentos_gen> medicamentos;

    public void setMedicamentos( List<Medicamentos_gen> medicamentos) {

        this.medicamentos = medicamentos;
    }

    public MedicamentosAdapter_gen(Context c) {
        this.c = c;
        this.medicamentos = new ArrayList<>();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView medi_imagen;
        TextView medi_nombre;
        TextView medi_informacion;
        TextView medi_precio;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medi_imagen = itemView.findViewById(R.id.image_medi);
            medi_nombre = itemView.findViewById(R.id.nombre_medi);
            medi_informacion = itemView.findViewById(R.id.informacion_medi);
            medi_precio = itemView.findViewById(R.id.precio_medi);

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_general, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Medicamentos_gen medicamentosGen = this.medicamentos.get(i);

        Context context = viewHolder.itemView.getContext();

        viewHolder.medi_nombre.setText(medicamentosGen.getNombre());
        viewHolder.medi_informacion.setText(medicamentosGen.getInformacion());
        viewHolder.medi_precio.setText(medicamentosGen.getPrecio());
        int idres = context.getResources().getIdentifier(medicamentosGen.getImagen(), "drawable", context.getPackageName());
        viewHolder.medi_imagen.setImageResource(idres);


        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            void onItemClickListener(View v, int position) {

                String Titulo = medicamentos.get(position).getNombre();
                String Descripcion = medicamentos.get(position).getInformacion();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) viewHolder.medi_imagen.getDrawable();

                Bitmap bitmap = bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] bytes = stream.toByteArray();

                Intent  intent = new Intent(c, InfoActivity.class);
                intent.putExtra("iTitle", Titulo);
                intent.putExtra("iDesc", Descripcion);
                intent.putExtra("iImage", bytes);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.medicamentos.size();
    }
}
