package com.example.container2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // CORREGIDO: Ahora usa android.widget
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactosViewHolder> {

    private List<ContactosModel> listaEntrenamientos;

    public ContactosAdapter(List<ContactosModel> listaEntrenamientos) {
        this.listaEntrenamientos = listaEntrenamientos;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        ContactosModel item = listaEntrenamientos.get(position);
        holder.tvTipo.setText("Entrenamiento: " + item.getTipo());
        holder.tvIntensidad.setText("Intensidad: " + item.getIntensidad());

        StringBuilder estrellas = new StringBuilder();
        for (int i = 0; i < item.getDificultad(); i++) {
            estrellas.append("⭐");
        }
        holder.tvDificultad.setText("Esfuerzo: " + estrellas.toString());
    }

    @Override
    public int getItemCount() {
        return listaEntrenamientos.size();
    }

    public static class ContactosViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipo, tvIntensidad, tvDificultad;

        public ContactosViewHolder(@NonNull View itemView) {
            super(itemView); // CORREGIDO: Se cambió 'holder' por 'itemView'
            tvTipo = itemView.findViewById(R.id.tvItemTipo);
            tvIntensidad = itemView.findViewById(R.id.tvItemIntensidad);
            tvDificultad = itemView.findViewById(R.id.tvItemDificultad);
        }
    }
}
