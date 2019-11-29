package com.example.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.Models.Filme;
import com.example.movielist.Models.Filmefavoritos;

import java.util.List;

public class Adapterfavoritas extends RecyclerView.Adapter<Adapterfavoritas.MyViewHolder> {
    private List<Filmefavoritos> listafilme;

    public Adapterfavoritas(List<Filmefavoritos> lista) {
        this.listafilme = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoritas, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Filmefavoritos favoritos = listafilme.get(position);
        holder.titulo.setText(favoritos.getNome());
        holder.ano.setText(String.valueOf(favoritos.getId()));

    }



    @Override
    public int getItemCount() {
        return listafilme.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, ano;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo1);
            ano = itemView.findViewById(R.id.ano2);
        }
    }
}