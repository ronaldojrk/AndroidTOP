package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
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
    ListView viu;
    List<Filme>tu;
    List<String> nomes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmesteste);
        TOP = (TextView)findViewById(R.id.nomefilme);
        viu = (ListView) findViewById(R.id.listviu);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }

        NodeServerFilme service = RetrofitClientFilme.getRetrofitInstance().create(NodeServerFilme.class);
        Call<Example> call = service.ListarFilmes();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    Log.e("ONRESPONSE","LOGADO COM SUCESSO");
                    tu =new ArrayList<>();

                    tu = response.body().getFilmes();
                    
                    // TOP.setText(tu.get(1).getTitle());
                    for (Filme aluno: tu) {


                        nomes.add(aluno.getTitle());
                    }
                    ArrayAdapter ar = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,nomes);

                    viu.setAdapter(ar);


                }else{
                    Log.e("ONRESPONSE","CREDENCIAIS INVÁLIDAS");
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


    }



    //TOP.setText(tu.get(1).getTitle());


}
