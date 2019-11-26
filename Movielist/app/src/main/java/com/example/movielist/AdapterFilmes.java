package com.example.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.Models.Filme;

import java.util.List;

public class AdapterFilmes  extends RecyclerView.Adapter<AdapterFilmes.MyViewHolder> {
    private List<Filme> listafilme;
    public AdapterFilmes(List<Filme> lista) {
        this.listafilme=lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.filmes,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
       Filme filmex =listafilme.get(position);
        holder.titulo.setText(filmex.getTitle());
        holder.ano.setText(filmex.getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return listafilme.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo,ano;
    public MyViewHolder(View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.titulo);
        ano = itemView.findViewById(R.id.ano);
    }
}

}
