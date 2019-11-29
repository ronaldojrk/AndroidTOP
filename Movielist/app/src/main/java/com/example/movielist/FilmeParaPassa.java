package com.example.movielist;

import java.io.Serializable;

public class FilmeParaPassa implements Serializable {
    public Integer id;
    public String posterPath;
    public String title;
    public String overview;
    public Double voteAverage;
    public String releaseDate;
    public Double popularity;

    public FilmeParaPassa(Integer id, String posterPath, String title, String overview, Double voteAverage, String releaseDate, Double popularity) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public FilmeParaPassa() {

    }
}
