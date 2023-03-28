package TestBHDStar.Service.Impl;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.DTO.Page.MoviePageDTO;
import TestBHDStar.Exception.FilmActionException;
import TestBHDStar.Exception.MovieNotFoundException;
import TestBHDStar.Repository.MovieRepository;
import TestBHDStar.Repository.CustomizedRepository.MovieSearchRepository;
import TestBHDStar.Service.MovieService;
import TestBHDStar.entity.MovieEntity;
import TestBHDStar.mapper.mapperImpl.MovieMapper;
import TestBHDStar.utils.ThumbnailFileUtil;
import org.hibernate.search.engine.search.query.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Value("${MOVIE_PAGE_SIZE}")
    private int MOVIE_PAGE_SIZE;
    @Autowired
    MovieRepository movieRepository;


    @Override
    public MovieDTO save(MovieEntity movie) {

        try{
            return MovieMapper.getInstance().toDTO(movieRepository.save(movie));
        }
        catch (Exception e) {
            throw new FilmActionException("Cập nhật phim không thành công! Bạn vui lòng tạo lại!");
        }

    }

    @Override
    public List<MovieDTO> findAll() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        movies.forEach(movie->{
            movieDTOList.add(MovieMapper.getInstance().toDTO(movie));
        });
        return  movieDTOList;
 }

    @Override
    public MovieDTO findById(int id) {
        Optional<MovieEntity> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            MovieDTO movieDTO = MovieMapper.getInstance().toDTO(optionalMovie.get());
            return  movieDTO;
        } else {
            throw  new MovieNotFoundException("Phim không tồn tại! Bạn vui lòng thử lại!");
        }
    }

    @Override
    public MovieDTO updateNoImage(MovieEntity movieEntity) {
        MovieEntity movie = movieRepository.findById(movieEntity.getId()).get();
        movieEntity.setThumbnail(movie.getThumbnail());
        return  MovieMapper.getInstance().toDTO(movieRepository.save(movieEntity));
    }

    @Override
    public MoviePageDTO searchByTitle(String title,int page) {
        SearchResult<MovieEntity> results = movieRepository.searchByTitle(title, page);
        long totalHits = results.total().hitCount();
        List<MovieEntity> movieEntityList = results.hits();
        MoviePageDTO moviePageDTO = new MoviePageDTO();
        moviePageDTO.setMovieDTOList(MovieMapper.getInstance().toDTOList(movieEntityList));
        moviePageDTO.setTotalItemPage(totalHits);
        moviePageDTO.setTotalPage((long) Math.ceil((double) totalHits / MOVIE_PAGE_SIZE));
        return moviePageDTO;
    }

    @Override
    public List<MovieDTO> searchAllByTitle(String title) {
        return movieRepository.searchAllByTitle(title);
    }

    @Override
    public MoviePageDTO findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, MOVIE_PAGE_SIZE);
        Page<MovieEntity> movieEntityPage = movieRepository.findAll(pageable);
        MoviePageDTO moviePageDTO = new MoviePageDTO();
        moviePageDTO.setMovieDTOList(MovieMapper.getInstance().toDTOList(movieEntityPage.getContent()));
        moviePageDTO.setTotalItemPage(movieEntityPage.getTotalElements());
        moviePageDTO.setTotalPage(movieEntityPage.getTotalPages());
        return moviePageDTO;
    }

    @Override
    public void delete(int id) {
        MovieEntity movieEntity;
      Optional<MovieEntity> optionalMovie = movieRepository.findById(id);
      if (optionalMovie.isPresent()) {
          movieEntity= optionalMovie.get();
      } else {
          throw new MovieNotFoundException("Bộ phim không tồn tại");
      }
  ThumbnailFileUtil.getInstance().deleteImageInDisk(movieEntity.getThumbnail());
      movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO createOrUpdate(MultipartFile image, MovieEntity movieEntity) {
        movieEntity.setThumbnail(ThumbnailFileUtil.getInstance().saveThumbnailToDisk(image));
        return  MovieMapper.getInstance().toDTO(movieRepository.save(movieEntity));
    }



}
