package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movielist.Models.Filme;
import com.example.movielist.Models.FilmefavoritoDAO;
import com.example.movielist.Models.Filmefavoritos;
import com.example.movielist.conexao.ConexaoUtil;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {
    ArrayList<Filmefavoritos> filmes;
    RecyclerView mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);
        //mostrar = (TextView) findViewById(R.id.textfilme);
        mostrar = (RecyclerView) findViewById(R.id.todo);


        FilmefavoritoDAO crud = new FilmefavoritoDAO(getApplicationContext());

        Cursor dao =crud.carregaDados();
        filmes =new ArrayList<>();
        while (dao.moveToNext())
        {

            String id =dao.getString(dao.getColumnIndex(ConexaoUtil.ID));
            String nome =dao.getString(dao.getColumnIndex(ConexaoUtil.NOME));
            String idfilmes =dao.getString(dao.getColumnIndex(ConexaoUtil.ID_FILME));
            String iduser =dao.getString(dao.getColumnIndex(ConexaoUtil.ID_USER));
            Filmefavoritos ron =new Filmefavoritos (Integer.parseInt(id),nome,idfilmes,iduser);
            filmes.add(ron);
        }

        Adapterfavoritas adapter = new Adapterfavoritas(filmes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mostrar.setLayoutManager(layoutManager);
        mostrar.setHasFixedSize(true);
        mostrar.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        mostrar.setAdapter(adapter);
    }
}
