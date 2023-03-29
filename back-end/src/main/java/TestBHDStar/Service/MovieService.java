package TestBHDStar.service;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.DTO.Page.MoviePageDTO;
import TestBHDStar.entity.MovieEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    MovieDTO createMovie(MovieEntity movie);
    List<MovieDTO> findAll();

    MoviePageDTO findMovieByPage(int pageNumber);
    void deleteMovie(int id);
    MovieDTO createOrUpdateMovie(MultipartFile image,MovieEntity movieEntity);
    MovieDTO findMovieById(int id);

    MovieDTO updateMovieNoImage(MovieEntity movieEntity);
    MoviePageDTO searchMovieByTitle(String title, int page);
    List<MovieDTO> searchAllMovieByTitle(String title);
}
