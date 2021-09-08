package com.example.actors.repository;

import com.example.actors.model.Movie;

public interface MovieRepository {

    void addMovieBaseInfo(String movieURI, Movie movie);

    void addMovieProducers(String movieURI, Movie movie);

    void addMovieProductionCompanies(String movieURI, Movie movie);

    void addMovieActors(String movieURI, Movie movie);


}
