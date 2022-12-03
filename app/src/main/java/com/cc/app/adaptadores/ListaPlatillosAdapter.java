package com.cc.app.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cc.app.VerPlatillo;
import com.cc.app.entidades.Platillo;
import com.cc.app.R;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListaPlatillosAdapter extends RecyclerView.Adapter<ListaPlatillosAdapter.PlatilloViewHolder> {

    ArrayList<Platillo> listaPlatillos;
    ArrayList<Platillo> listaOriginal;


    public ListaPlatillosAdapter(ArrayList<Platillo> listaPlatillos) {
        this.listaPlatillos = listaPlatillos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPlatillos);
    }

    @NonNull
    @Override
    public PlatilloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_platillo, null, false);
        return new PlatilloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatilloViewHolder holder, int position) {

        String viewNombre = listaPlatillos.get(position).getNombre_platillo(),
                viewPrecio = listaPlatillos.get(position).getDescripcion(),
                viewDescription = listaPlatillos.get(position).getDescripcion();


        holder.viewNombre.setText(viewNombre);
        holder.viewPrecio.setText(viewPrecio.split("--")[0]);
        holder.viewDescripcion.setText(viewDescription.split("--")[1]);


    }

    @Override
    public int getItemCount() {
        return listaPlatillos.size();
    }

    public class PlatilloViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewPrecio, viewDescripcion;

        public PlatilloViewHolder(@NonNull View itemView) {

            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombrePlatillo);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    Intent intent = new Intent(context, VerPlatillo.class);
                    intent.putExtra("id", listaPlatillos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }

    }
}
