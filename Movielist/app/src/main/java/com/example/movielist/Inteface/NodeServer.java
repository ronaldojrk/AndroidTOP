package com.example.movielist.Inteface;

import com.example.movielist.Models.Filmefavoritos;
import com.example.movielist.Models.Resposta;
import com.example.movielist.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodeServer {
    @FormUrlEncoded
    @POST("/login")
    Call<Usuario> login(@Field("email") String email, @Field("senha") String senha);

    @FormUrlEncoded
    @POST("/sessions")
    Call<Usuario> criarsessions (@Field("nome") String nome,@Field("email") String email, @Field("senha") String senha);

    @GET("/lista")
    Call<List<Usuario>> ListarUsuarios();

    @FormUrlEncoded
    @POST("/criarfavo")
    Call<Filmefavoritos> favoritos(@Field("nome") String nome,@Field("id_filme") String idfilme, @Field("id_user") String idusuario);

    @DELETE("/removerfavoritos")
    Call<Resposta> favoritosdele(@Field("nome") String nome, @Field("id_filme") String idfilme, @Field("id_user") String idusuario);


}
