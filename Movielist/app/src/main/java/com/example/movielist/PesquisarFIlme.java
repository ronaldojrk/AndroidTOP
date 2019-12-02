package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.movielist.ApiFilme.RetrofitClientFilme;
import com.example.movielist.InterfaceFilme.NodeServerFilme;
import com.example.movielist.Models.Example;
import com.example.movielist.Models.Filme;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarFIlme extends AppCompatActivity {
        EditText nome;
        Button pesquisar,menupesquisar,voltar2,avancar2;
    RecyclerView RecyFilmes2;
    List<Filme> filmes;
    List<String> nomes;
    String texto;
    int pagina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_filme);
        nome = (EditText) findViewById(R.id.editText2);
        pesquisar = (Button) findViewById(R.id.butonconfim);
        menupesquisar = (Button) findViewById(R.id.voltandohome);
        RecyFilmes2 = (RecyclerView) findViewById(R.id.lista_filmes2);
        voltar2 = (Button) findViewById(R.id.fragment_voltar2);
        avancar2 = (Button) findViewById(R.id.fragment_Avancar2);


        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome2 =nome.getText().toString();
                //pesquisartudo(nome2);
                String url ="3/search/movie?api_key=0e03db20142a60bdb0d54b4087ad42fa&language=pt-BR&query=";
                String url2 = url.concat(nome2);
                texto =url2;
                pesquisartudo(url2);
            }
        });

        menupesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                startActivity(i);
            }
        });
        avancar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagina++;
                String page ="&page=";
                String page2 = page.concat(String.valueOf(pagina));
                String page3 = texto.concat(page2);
                pesquisartudo(page3);
            }
        });
        voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pagina>0){
                    pagina--;
                }
                String page ="&page=";
                String page2 = page.concat(String.valueOf(pagina));
                String page3 = texto.concat(page2);
                pesquisartudo(page3);
            }
        });

    }
    private void pesquisartudo(String titulo ) {
        NodeServerFilme service = RetrofitClientFilme.getRetrofitInstance().create(NodeServerFilme.class);
        Call<Example> call = service.Pesquisar(titulo);

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
                    // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
                    RecyFilmes2.setLayoutManager(layoutManager);
                    RecyFilmes2.setHasFixedSize(true);
                    RecyFilmes2.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    RecyFilmes2.setAdapter(adapter);
                    RecyFilmes2.addOnItemTouchListener(
                            new RecyclerItemClickListener(
                                    getApplicationContext(),
                                    RecyFilmes2,
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent i = new Intent(getApplicationContext(), DetalhesFilme.class);
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
