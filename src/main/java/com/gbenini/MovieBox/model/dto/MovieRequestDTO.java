package com.gbenini.MovieBox.model.dto;

import com.gbenini.MovieBox.model.entity.Movie;
import com.gbenini.MovieBox.model.enumerate.genreEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRequestDTO(

        @NotBlank(message = "Title can not be null")
        String title,
        @NotBlank(message = "Director can not be null")
        String director,
        @NotNull(message = "Release year can not be null")
        Integer releaseYear,
        @NotNull(message = "Genre can not be null")
        genreEnum genre,
        @NotNull(message = "Rating can not be null")
        Double rating) {

    public Movie toEntity(){

        Movie movie = new Movie();

        movie.setTitle(this.title);
        movie.setDirector(this.director);
        movie.setReleaseYear(this.releaseYear);
        movie.setGenre(this.genre);
        movie.setRating(this.rating);

        return movie;

    }


}
