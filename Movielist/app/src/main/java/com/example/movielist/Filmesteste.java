package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movielist.ApiFilme.RetrofitClientFilme;
import com.example.movielist.Inteface.Organization;
import com.example.movielist.InterfaceFilme.NodeServerFilme;
import com.example.movielist.Models.Example;
import com.example.movielist.Models.Filme;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Filmesteste extends AppCompatActivity {
    TextView TOP;
     private RecyclerView RecyFilmes;
    List<Filme>filmes;
    List<String> nomes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmesteste);
        ///TOP = (TextView)findViewById(R.id.nomefilme);
        RecyFilmes = (RecyclerView)findViewById(R.id.recy);
        //adapter
        //AdapterFilmes adapter = new AdapterFilmes();
        //configurando o Recy
        /*RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyFilmes.setLayoutManager(layoutManager);
       RecyFilmes.setHasFixedSize(true);*
        RecyFilmes.setAdapter(adapter);*/
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
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
                if(response.isSuccessful()){
                    Log.e("ONRESPONSE","LOGADO COM SUCESSO");
                    Example exemple =response.body();
                    filmes= exemple.getFilmes();
                   // TOP.setText(filmes.get(6).getTitle());
                    AdapterFilmes adapter = new AdapterFilmes(filmes);
                    //configurando o Recy
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    RecyFilmes.setLayoutManager(layoutManager);
                    RecyFilmes.setHasFixedSize(true);
                    RecyFilmes.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayout.VERTICAL));
                    RecyFilmes.setAdapter(adapter);
                    RecyFilmes.addOnItemTouchListener(
                            new RecyclerItemClickListener(
                                    getApplicationContext(),
                                    RecyFilmes,
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
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



                }else{
                    Log.e("ONRESPONSE","CREDENCIAIS INVÁLIDAS");
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }


}
