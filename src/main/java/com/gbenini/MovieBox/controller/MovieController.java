package com.gbenini.MovieBox.controller;

import com.gbenini.MovieBox.model.dto.MovieRequestDTO;
import com.gbenini.MovieBox.model.dto.MovieResponseDTO;
import com.gbenini.MovieBox.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody @Valid MovieRequestDTO requestDTO){

        MovieResponseDTO responseDTO = movieService.createMovie(requestDTO);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping
    public ResponseEntity<Page<MovieResponseDTO>> listMovies(Pageable pageable){

        Page<MovieResponseDTO> responseDTO = movieService.listMovies(pageable);
        return ResponseEntity.ok(responseDTO);

    }


    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double rating){

        List<MovieResponseDTO> responseDTO = movieService.searchMovies(title,director,releaseYear,genre,rating);
        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable("id") Long id, @RequestBody @Valid MovieRequestDTO requestDTO ){

        MovieResponseDTO responseDTO = movieService.updateMovie(id, requestDTO);
        return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id){

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
