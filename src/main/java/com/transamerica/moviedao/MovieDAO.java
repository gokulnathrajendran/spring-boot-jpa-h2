package com.transamerica.moviedao;

import com.transamerica.model.Movie;

import java.util.List;

public interface MovieDAO {

    List<Movie> getAllMovies();
    Movie findMovieByID(long id);
    Movie saveMovie(Movie movie);
    void deleteMovieByID(long id);
    void deleteAllMovie();

}
