package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.service.MovieSystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieSystemController {
    private final MovieSystemService movieSystemService;

    public MovieSystemController(MovieSystemService movieSystemService) {
        this.movieSystemService = movieSystemService;
    }

    @GetMapping("/movieSystems")
    List<MovieSystemDTO> findAll() {
        return movieSystemService.findAll();
    }
}
