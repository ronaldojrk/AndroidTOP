package com.example.movielist.InterfaceFilme;

import com.example.movielist.Models.Example;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NodeServerFilme {

    @GET("3/discover/movie?api_key=0e03db20142a60bdb0d54b4087ad42fa&language=pt-BR&sort_by=popularity.desc")
    Call<Example> ListarFilmes();

    @GET("3/search/movie?api_key=0e03db20142a60bdb0d54b4087ad42fa&language=pt-BR&query={pesquisar}")
    Call<Example> PesquisarFilmes(@Path("pesquisar") String pes);


}
