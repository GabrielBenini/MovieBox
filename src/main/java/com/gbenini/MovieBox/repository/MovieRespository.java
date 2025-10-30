package com.gbenini.MovieBox.repository;

import com.gbenini.MovieBox.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRespository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByDirector(String director);

    List<Movie> findByReleaseYear(Integer releaseYear);

    List<Movie> findByGenre(String genre);

    List<Movie> findByRating(Double rating);

}
