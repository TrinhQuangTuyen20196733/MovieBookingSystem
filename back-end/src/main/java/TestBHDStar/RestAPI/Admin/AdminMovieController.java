package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.DTO.Page.MoviePageDTO;
import TestBHDStar.DTO.ResponseMessage;
import TestBHDStar.Exception.FilmActionException;
import TestBHDStar.Service.MovieService;
import TestBHDStar.entity.MovieEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminMovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieDTO> createMovie(@RequestParam("image") MultipartFile image, @RequestParam("movieInfo") String movieJson) {


        try {
            ObjectMapper mapper = new ObjectMapper();
            MovieEntity movie = mapper.readValue(movieJson, MovieEntity.class);
            return ResponseEntity.status(HttpStatus.OK).body(movieService.createOrUpdate(image, movie));
        } catch (IOException e) {

            System.out.println(e.getMessage());
            throw new FilmActionException("Thêm phim không thành công! Bạn vui lòng tạo lại!");
        }

    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@RequestParam("image") MultipartFile image, @RequestParam("movieInfo") String movieJson, @PathVariable @Positive int id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MovieEntity movie = mapper.readValue(movieJson, MovieEntity.class);
            return ResponseEntity.status(HttpStatus.OK).body(movieService.createOrUpdate(image, movie));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new FilmActionException("Cập nhật phim không thành công! Bạn vui lòng tạo lại!");
        }

    }

    @PutMapping("/movies/noImageUpdate/{id}")
    public ResponseEntity<MovieDTO> updateMovieNoImagedUpdate(@RequestBody MovieEntity movieEntity, @PathVariable @Positive int id) {

        return  ResponseEntity.status(HttpStatus.OK).body(movieService.updateNoImage(movieEntity));

    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovieByID(@PathVariable @Positive int id) {
        MovieDTO movieDTO = movieService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTO);
    }

    @GetMapping("/movies/pages/{pageNumber}")
    public MoviePageDTO findPage(@PathVariable int pageNumber) {
        return movieService.findPage(pageNumber);
    }
    @GetMapping("/movies/search/pages/{pageNumber}")
    public MoviePageDTO searchByTitle(@RequestParam String title, @PathVariable int pageNumber){
        return movieService.searchByTitle(title,pageNumber);
    }
    @GetMapping("/movies/search")
    public List<MovieDTO> searchAllByTitle(@RequestParam String title){
        return movieService.searchAllByTitle(title);
    }

    @DeleteMapping("/movies/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable @Positive int id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Phim đã được xóa thành công"));
    }

}
