package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.Service.MovieSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieSystemController {
    @Autowired
    MovieSystemService movieSystemService;
    @GetMapping("/movieSystems")
    List<MovieSystemDTO> findAll() {
        return  movieSystemService.findAll();
    }
}
