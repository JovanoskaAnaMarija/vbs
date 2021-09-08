package com.example.actors.controller;

import com.example.actors.model.Movie;
import com.example.actors.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public Movie getMovieInfo(@RequestParam("movie") String searchMovie) {
        return this.movieService.getMovieInfo(searchMovie);
    }

}
