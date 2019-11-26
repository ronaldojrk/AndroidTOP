package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

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
                    tvTitulo.setText("Favorito");
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
