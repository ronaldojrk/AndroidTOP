package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalhesFilme extends AppCompatActivity {
    ImageView imageView;
    TextView tvTitulo;
    TextView tvGenero;
    TextView tvAno;
    TextView tvNota;
    TextView tvResumo;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        imageView = findViewById(R.id.imageView2);
        tvAno = findViewById(R.id.tvAno);
        tvGenero = findViewById(R.id.tvGenero);
        tvResumo = findViewById(R.id.tvResumo);
        tvNota = findViewById(R.id.tvNota);
        ratingBar = findViewById(R.id.ratingBar);
        tvTitulo = findViewById(R.id.tvTitulo);
        Picasso.get().load("https://image.tmdb.org/t/p/w500//5myQbDzw3l8K9yofUXRJ4UTVgam.jpg")
                .resize(430,200)
                .into(imageView);
    }
}
