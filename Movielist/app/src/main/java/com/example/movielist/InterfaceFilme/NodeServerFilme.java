package com.example.movielist.InterfaceFilme;

import com.example.movielist.Models.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NodeServerFilme {

    @GET("3/discover/movie?api_key=0e03db20142a60bdb0d54b4087ad42fa&language=pt-BR&sort_by=popularity.desc")
    Call<Example> ListarFilmes();
}
