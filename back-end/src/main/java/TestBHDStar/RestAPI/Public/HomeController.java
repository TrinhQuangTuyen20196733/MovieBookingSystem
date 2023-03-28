package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    MovieService movieService;

@GetMapping("/movies")
public List<MovieDTO> getExample() {
    return  movieService.findAll();
}

}
