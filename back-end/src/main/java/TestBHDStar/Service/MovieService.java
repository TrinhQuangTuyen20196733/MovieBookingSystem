package TestBHDStar.Service;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.DTO.Page.MoviePageDTO;
import TestBHDStar.entity.MovieEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieDTO save(MovieEntity movie);
    List<MovieDTO> findAll();

    MoviePageDTO findPage(int pageNumber);
    void delete(int id);
    MovieDTO createOrUpdate(MultipartFile image,MovieEntity movieEntity);
    MovieDTO findById(int id);

    MovieDTO updateNoImage(MovieEntity movieEntity);
    MoviePageDTO searchByTitle(String title, int page);
    List<MovieDTO> searchAllByTitle(String title);
}
