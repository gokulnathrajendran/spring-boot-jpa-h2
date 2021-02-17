package com.transamerica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.transamerica.model.Movie;
import com.transamerica.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class MovieController {
	@Autowired
	MovieRepository movieRepository;

	//For getting all the movies
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> findAllMovies(@RequestParam(required = false) String title){
		try{
			List<Movie> movies = new ArrayList<>();

			if(title == null)
				movieRepository.findAll().forEach(movies::add);
			else
				movieRepository.findByTitleContaining(title).forEach(movies::add);

			if(movies.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(movies,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//For getting whether the movie with a given id if present or new empty movie object.
	@GetMapping("movies/{id}")
	public ResponseEntity<Movie> findMovieById(@PathVariable("id") long id){
		Optional<Movie> movieData = movieRepository.findById(id);

		if(movieData.isPresent()) {
			return new ResponseEntity<>(movieData.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//For adding a movie
	@PostMapping("/movies")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		try {
			Movie _movie = movieRepository.save(new Movie(movie.getTitle(),movie.getDescription(),true));
			return new ResponseEntity<>(_movie,HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//For updating a movie
	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie ){
		Optional<Movie> movieData = movieRepository.findById(id);

		if(movieData.isPresent()){
			Movie _movie = movieData.get();
			_movie.setTitle(movie.getTitle());
			_movie.setDescription(movie.getDescription());
			_movie.setAvailable(movie.isAvailable());

			return new ResponseEntity<>(movieRepository.save(_movie),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//For deleting a movie
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long movieId){
		try {
			movieRepository.deleteById(movieId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/movies")
	public ResponseEntity<HttpStatus> deleteAllMovies() {
		try {
			movieRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/movies/available")
	public ResponseEntity<List<Movie>> findByAvailable(){
		try{
			List<Movie> movies = movieRepository.findByAvailable(true);

			if(movies.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(movies,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}