package com.transamerica.repository;

import com.transamerica.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByAvailable(boolean available);
    List<Movie> findByTitleContaining(String title);

}
