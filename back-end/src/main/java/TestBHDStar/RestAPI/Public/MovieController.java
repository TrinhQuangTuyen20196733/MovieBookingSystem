package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.service.MovieService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    final
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> findMovieById(@PathVariable @Positive int id) {
        MovieDTO movieDTO = movieService.findMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTO);
    }

    @GetMapping("/movies")
    public List<MovieDTO> findAll() {
        return  movieService.findAll();
    }
}
