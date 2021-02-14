package com.transamerica.moviedao;

import com.transamerica.model.Movie;
import com.transamerica.moviedao.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MovieDAOJpaImpl implements MovieDAO {
    private EntityManager entityManager;

    @Autowired
    public MovieDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;

    }

    //get all the movies from the database
    @Override
    public List<Movie> getAllMovies() {
        Query query= (Query) entityManager.createQuery("from movie");
        List<Movie> transactions = query.getResultList();
        return transactions;
    }

    //return the movie by giving id as input
    @Override
    public Movie findMovieByID(long id) {
        Movie movie = entityManager.find(Movie.class,id);
        return movie;
    }

    //add the movie to the database
    @Override
    public Movie saveMovie(Movie movie) {
        Movie dbTransaction = entityManager.merge(movie);
        movie.setId(dbTransaction.getId());
        return movie;
    }

    //delete the movie from the database using movie id
    @Override
    public void deleteMovieByID(long id) {
        Query query = (Query) entityManager.createQuery("delete from movie where id=:movieid");
        query.setParameter("movieid", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAllMovie(){
        Query query = (Query) entityManager.createQuery("delete from movie");
        query.executeUpdate();
    }
}
