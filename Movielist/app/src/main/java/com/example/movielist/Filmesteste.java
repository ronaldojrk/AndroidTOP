package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import com.example.movielist.ApiFilme.RetrofitClientFilme;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmesteste);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        TOP = (TextView)findViewById(R.id.nomefilme);

        NodeServerFilme service = RetrofitClientFilme.getRetrofitInstance().create(NodeServerFilme.class);
        Call<Example> call = service.ListarFilmes();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    Log.e("ONRESPONSE","LOGADO COM SUCESSO");

                    ArrayList<Filme>ri = new ArrayList<>();
                    List<Filme>tu = response.body().getFilmes();
                   // ri=response.body().getFilmes();
                    final int page =response.body().getPage();
                    final int results=response.body().getTotalResults();
                    final String title =response.body().getFilmes().get(1).getTitle();

                    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString(getString(R.string.pref_email), response.body().getEmail());
                    //editor.putString(getString(R.string.pref_nome), response.body().getNome());

                    editor.apply();
                    TOP.setText(tu.get(1).getTitle());


                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


    }
}
