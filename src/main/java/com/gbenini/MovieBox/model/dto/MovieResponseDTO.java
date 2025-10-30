package com.gbenini.MovieBox.model.dto;

import com.gbenini.MovieBox.model.entity.Movie;
import com.gbenini.MovieBox.model.enumerate.genreEnum;

public record MovieResponseDTO(Long id, String title, String director, Integer releaseYear, genreEnum genre, Double rating) {

    public static MovieResponseDTO fromEntity(Movie movie){

        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getReleaseYear(),
                movie.getGenre(),
                movie.getRating()
        );

    }

}
