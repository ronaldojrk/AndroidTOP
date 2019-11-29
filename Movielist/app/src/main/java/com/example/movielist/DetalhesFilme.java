package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.movielist.Api.RetrofitClient;
import com.example.movielist.FuncoesEstaticas.FuncoesEstaticas;
import com.example.movielist.Inteface.NodeServer;
import com.example.movielist.Models.FilmefavoritoDAO;
import com.example.movielist.Models.Filmefavoritos;
import com.example.movielist.Models.Usuario;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesFilme extends AppCompatActivity {
    ImageView imageView;
    TextView tvTitulo;
    TextView tvGenero;
    TextView tvAno;
    TextView tvNota;
    TextView tvResumo;
    RatingBar ratingBar;
    ImageView voltar;
    ToggleButton favorito;
    ToggleButton visualizado;
    public static final String EXTRA_ALUNO = "aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        imageView = findViewById(R.id.imageView2);
        tvAno = findViewById(R.id.tvAno);
        tvResumo = findViewById(R.id.tvResumo);
        tvNota = findViewById(R.id.tvNota);
        ratingBar = findViewById(R.id.ratingBar);
        tvTitulo = findViewById(R.id.tvTitulo);
        favorito = findViewById(R.id.button_favorite);
        visualizado = findViewById(R.id.button_visualizar);

        Intent intent = getIntent();
        FilmeParaPassa filme23= (FilmeParaPassa) intent.getSerializableExtra(EXTRA_ALUNO);
        tvTitulo.setText(filme23.title);
        tvResumo.setText(filme23.overview);
        tvAno.setText(filme23.releaseDate);
        tvNota.setText("nota:" + String.valueOf(filme23.voteAverage/2)+"/5");
        ratingBar.setRating((float) (filme23.voteAverage/2));

        visualizado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        tvTitulo.setText("Vizualizar");
                    }else{

                    }
            }
        });
        favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //tvTitulo.setText("Favorito");

                    if(!FuncoesEstaticas.isConnected(getApplicationContext())){
                        Toast toast = Toast.makeText(getApplicationContext(), "Sem conexão", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    String nome =  tvTitulo.getText().toString();
                    String id_filme =  String.valueOf(filme23.id);
                    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
                    String result = sharedPreferences.getString(getString(R.string.pref_email), "");
                    String id_user =result;



                    Filmefavoritos filmefavoritos = new Filmefavoritos(nome,id_filme, id_user);
                    Log.i("PRINT", filmefavoritos.getNome());

                    NodeServer service = RetrofitClient.getRetrofitInstance().create(NodeServer.class);
                    Call<Filmefavoritos> call = service.favoritos(nome,id_filme,id_user);
                    call.enqueue(new Callback<Filmefavoritos>() {
                        @Override
                        public void onResponse(Call<Filmefavoritos> call, Response<Filmefavoritos> response) {
                            if(response.isSuccessful()){
                                Log.i("PRINT", "Criado");
                                FilmefavoritoDAO crud =new FilmefavoritoDAO(getApplicationContext());
                                    String resultado =crud.insereDado(filmefavoritos);
                                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("nome",nome);
                                returnIntent.putExtra("id_user",id_user);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }else{
                                Log.i("PRINT", "NÃO Criado");
                            }

                        }
                        @Override
                        public void onFailure(Call<Filmefavoritos> call, Throwable t) {
                            Log.e("CriarUsuario", t.getMessage());
                        }
                    });


                }else{
                    
                }
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+filme23.posterPath)
                .resize(430,200)
                .into(imageView);

    }
    public void voltar(){
        finish();
    }
}
