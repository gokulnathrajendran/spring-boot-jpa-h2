package com.transamerica.controller;

import java.util.List;
import java.util.Optional;

import com.transamerica.model.Movie;
import com.transamerica.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="/api")
public class MovieController {

	private MovieService movieService;

	@Autowired
	public MovieController(MovieService theMovieService){
		movieService = theMovieService;

	}
	//For getting all the movies
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> findAll(){
		return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
	}

	//For getting whether the movie with a given id if present or new empty movie object.
	@GetMapping("movies/{id}")
	public ResponseEntity<Movie> findMovieById(@PathVariable("id") long id){
		Optional<Movie> movieData = Optional.ofNullable(movieService.findMovieByID(id));

		if(movieData.isPresent()) {
			return new ResponseEntity<>(movieData.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//For adding a movie
	@PostMapping("/movies")
	public Movie addMovie(@RequestBody Movie movie){
		return (movieService.saveMovie(new Movie(movie.getTitle(),movie.getDescription(),true)));
	}

	//For updating a movie
	@PutMapping("/movies")
	public Movie updateMovie(@RequestBody Movie movie){
		Movie resultMovie = movieService.findMovieByID(movie.getId());
		if (resultMovie == null) {
			throw new RuntimeException("Movie to update doesn't exist");
		}
		return (movieService.saveMovie(movie));
	}

	//For deleting a movie
	@DeleteMapping("/movies/{id}")
	public String deleteMovie(@PathVariable("id") long movieId){
		Movie tempMovie = movieService.findMovieByID(movieId);
		if(tempMovie == null){
			throw new RuntimeException("Movie Id not found");
		}
		movieService.deleteMovieById(movieId);
		return "deleted movie id =" + movieId;

	}

	@DeleteMapping("/movies")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			movieService.deleteAllMovie();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}