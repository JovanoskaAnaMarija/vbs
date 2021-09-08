package com.example.actors.service.impl;

import com.example.actors.model.Movie;
import com.example.actors.repository.AssetsRepository;
import com.example.actors.repository.MovieRepository;
import com.example.actors.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final AssetsRepository assetsRepository;

    public MovieServiceImpl(MovieRepository movieRepository, AssetsRepository assetsRepository) {
        this.movieRepository = movieRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Movie getMovieInfo(String searchMovie) {
        String movieURI = this.assetsRepository.createEntityIdentifier(searchMovie);

        Movie movie = new Movie();
        this.movieRepository.addMovieBaseInfo(movieURI, movie);
        this.movieRepository.addMovieProducers(movieURI, movie);
        this.movieRepository.addMovieActors(movieURI, movie);
        return movie;
    }
}

