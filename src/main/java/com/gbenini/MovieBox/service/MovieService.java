package com.gbenini.MovieBox.service;

import com.gbenini.MovieBox.exception.ResourceNotFoundException;
import com.gbenini.MovieBox.model.dto.MovieRequestDTO;
import com.gbenini.MovieBox.model.dto.MovieResponseDTO;
import com.gbenini.MovieBox.model.entity.Movie;
import com.gbenini.MovieBox.repository.MovieRespository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRespository movieRespository;

    public MovieResponseDTO createMovie(MovieRequestDTO requestDTO){

        Movie movie = requestDTO.toEntity();

        movieRespository.save(movie);

        return MovieResponseDTO.fromEntity(movie);

    }

    public Page<MovieResponseDTO> listMovies(Pageable pageable){

        return movieRespository.findAll(pageable)
                .map(MovieResponseDTO::fromEntity);
    }

    public List<MovieResponseDTO> searchMovies(String title, String director, Integer releaseYear, String genre, Double rating){

        if (title != null){
            return movieRespository.findByTitle(title)
                    .stream()
                    .map(MovieResponseDTO::fromEntity)
                    .toList();
        }

        if (director != null) {
            return movieRespository.findByDirector(director)
                    .stream()
                    .map(MovieResponseDTO::fromEntity)
                    .toList();
        }

        if (releaseYear != null) {
            return movieRespository.findByReleaseYear(releaseYear)
                    .stream()
                    .map(MovieResponseDTO::fromEntity)
                    .toList();
        }

        if (genre != null) {
            return movieRespository.findByGenre(genre)
                    .stream()
                    .map(MovieResponseDTO::fromEntity)
                    .toList();
        }

        if (rating != null) {
            return movieRespository.findByRating(rating)
                    .stream()
                    .map(MovieResponseDTO::fromEntity)
                    .toList();
        }

        return movieRespository.findAll()
                .stream()
                .map(MovieResponseDTO::fromEntity)
                .toList();

    }

    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO requestDTO){

        Movie movie = movieRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movie.setTitle(requestDTO.title());
        movie.setDirector(requestDTO.director());
        movie.setGenre(requestDTO.genre());
        movie.setReleaseYear(requestDTO.releaseYear());
        movie.setRating(requestDTO.rating());

        movieRespository.save(movie);

        return MovieResponseDTO.fromEntity(movie);

    }

    public void deleteMovie(Long id){

        Movie movie = movieRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieRespository.delete(movie);
    }



}
