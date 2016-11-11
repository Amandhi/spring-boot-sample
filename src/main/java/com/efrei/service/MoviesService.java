package com.efrei.service;

import com.efrei.model.Movie;
import com.efrei.model.MovieType;
import com.efrei.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/movies")
public class MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<Movie> movies = movieRepository.findAll();

        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity updateMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        movieRepository.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/init")
    public void initDatabase() {
        Movie interstellar = Movie.builder()
                .director("Christopher Nolan")
                .releaseDate(LocalDate.of(2014, Month.NOVEMBER, 7))
                .type(MovieType.SCIFI)
                .title("Interstellar")
                .build();

        Movie snowden = Movie.builder()
                .director("Oliver Stone")
                .releaseDate(LocalDate.of(2016, Month.SEPTEMBER, 16))
                .type(MovieType.DRAMA)
                .title("Snowden")
                .build();

        movieRepository.save(Arrays.asList(interstellar, snowden));
    }
}
