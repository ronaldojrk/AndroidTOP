package com.example.movielist.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.AdapterFilmes;
import com.example.movielist.ApiFilme.RetrofitClientFilme;
import com.example.movielist.DetalhesFilme;
import com.example.movielist.FilmeParaPassa;
import com.example.movielist.InterfaceFilme.NodeServerFilme;
import com.example.movielist.Models.Example;
import com.example.movielist.Models.Filme;
import com.example.movielist.R;
import com.example.movielist.RecyclerItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesFragment extends Fragment {
    TextView TOP;
    RecyclerView RecyFilmes;
    List<Filme> filmes;
    List<String> nomes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filmes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyFilmes = (RecyclerView) view.findViewById(R.id.lista_filmes);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        recuperarapi();
    }

    private void recuperarapi() {
        NodeServerFilme service = RetrofitClientFilme.getRetrofitInstance().create(NodeServerFilme.class);
        Call<Example> call = service.ListarFilmes();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Log.e("ONRESPONSE", "LOGADO COM SUCESSO");
                    Example exemple = response.body();
                    filmes = exemple.getFilmes();
                    // TOP.setText(filmes.get(6).getTitle());
                    AdapterFilmes adapter = new AdapterFilmes(filmes);
                    //configurando o Recy
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    RecyFilmes.setLayoutManager(layoutManager);
                    RecyFilmes.setHasFixedSize(true);
                    RecyFilmes.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                    RecyFilmes.setAdapter(adapter);
                    RecyFilmes.addOnItemTouchListener(
                            new RecyclerItemClickListener(
                                    getContext(),
                                    RecyFilmes,
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent i = new Intent(getContext(), DetalhesFilme.class);
                                            FilmeParaPassa novo = new FilmeParaPassa();
                                            novo.id = filmes.get(position).getId();
                                            novo.title = filmes.get(position).getTitle();
                                            novo.overview = filmes.get(position).getOverview();
                                            novo.popularity = filmes.get(position).getPopularity();
                                            novo.releaseDate = filmes.get(position).getReleaseDate();
                                            novo.voteAverage = filmes.get(position).getVoteAverage();
                                            novo.posterPath = filmes.get(position).getPosterPath();
                                            i.putExtra(DetalhesFilme.EXTRA_ALUNO, novo);

                                            startActivity(i);
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
                    //Filmesteste testeDeFilmes =filmes;
                    //for( int i=0;i<filmes.size();i++){
                    // nomes.add(filmes.get(i).getTitle());
                    // nomes.get(i);
                    // }


                } else {
                    Log.e("ONRESPONSE", "CREDENCIAIS INVÁLIDAS");
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
