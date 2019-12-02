package com.example.movielist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.Adapterfavoritas;
import com.example.movielist.Api.RetrofitClient;
import com.example.movielist.DrawerActivity;
import com.example.movielist.Favoritas;
import com.example.movielist.Inteface.NodeServer;
import com.example.movielist.Models.FilmefavoritoDAO;
import com.example.movielist.Models.Filmefavoritos;
import com.example.movielist.Models.Resposta;
import com.example.movielist.Models.Usuario;
import com.example.movielist.R;
import com.example.movielist.RecyclerItemClickListener;
import com.example.movielist.conexao.ConexaoUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritosFragment extends Fragment {
    ArrayList<Filmefavoritos> filmes;
    RecyclerView mostrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        mostrar = (RecyclerView) view.findViewById(R.id.todo);


        FilmefavoritoDAO crud = new FilmefavoritoDAO(getContext());

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mostrar.setLayoutManager(layoutManager);
        mostrar.setHasFixedSize(true);
        mostrar.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mostrar.setAdapter(adapter);
        mostrar.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        mostrar,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String nome =filmes.get(position).getNome();
                                String id_filme =filmes.get(position).getIdfilme();
                                String id_user =filmes.get(position).getIdusuario();
                                crud.deletaRegistro(filmes.get(position));

                              /*/ NodeServer service = RetrofitClient.getRetrofitInstance().create(NodeServer.class);
                                Call<Resposta> call = service.favoritosdele(nome,id_filme,id_user);
                                call.enqueue(new Callback<Resposta>() {
                                    @Override
                                   public void onResponse(Call<Resposta> call, Response<Resposta> response) {

                                    }
//
                                    @Override
                                    public void onFailure(Call<Resposta> call, Throwable t) {

                                    }
                               });

                               // crud.deletaRegistro(filmes.get(position));
*/
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
        adapter.notifyDataSetChanged();
    }

}
