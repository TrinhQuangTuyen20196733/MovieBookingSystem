package TestBHDStar.Repository.CustomizedRepository;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.entity.MovieEntity;
import org.hibernate.search.engine.search.query.SearchResult;

import java.util.List;

public interface MovieSearchRepository {
    List<MovieDTO> searchAllByTitle(String title);
    SearchResult<MovieEntity> searchByTitle(String title, int page);
}
