package com.transamerica.service;

import com.transamerica.model.Movie;
import com.transamerica.moviedao.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    MovieDAO movieDAO;

    @Autowired
    public MovieServiceImpl(@Qualifier("movieDAOJpaImpl") MovieDAO themovieDAO){
        movieDAO = themovieDAO;
    }

    @Override
    @Transactional
    public List<Movie> findAllMovies() {
        return movieDAO.getAllMovies();
    }

    @Override
    @Transactional
    public Movie findMovieByID(long id) {
        return movieDAO.findMovieByID(id);
    }

    @Override
    @Transactional
    public Movie saveMovie(Movie movie) {
        return movieDAO.saveMovie(movie);
    }

    @Override
    @Transactional
    public long deleteMovieById(long id) {
        movieDAO.deleteMovieByID(id);
        return id;
    }

    @Override
    @Transactional
    public void deleteAllMovie() {
        movieDAO.deleteAllMovie();
    }
}
