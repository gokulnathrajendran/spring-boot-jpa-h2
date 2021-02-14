package com.transamerica.service;

import com.transamerica.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAllMovies();
    Movie findMovieByID(long id);

    Movie saveMovie(Movie movie);

    long deleteMovieById(long id);

    void deleteAllMovie();

}
