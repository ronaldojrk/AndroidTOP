package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);
        //mostrar = (TextView) findViewById(R.id.textfilme);
        mostrar = (RecyclerView) findViewById(R.id.todo);
       menu = (Button) findViewById(R.id.menufavoritos);
       menu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
               startActivity(i);
           }
       });


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
        mostrar.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        mostrar,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                crud.deletaRegistro(filmes.get(position));
                                Intent i = new Intent(getApplicationContext(), Favoritas.class);
                                startActivity(i);
                                finish();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
}
