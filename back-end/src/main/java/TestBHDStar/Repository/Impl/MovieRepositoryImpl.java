package TestBHDStar.Repository.Impl;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.Repository.CustomizedRepository.MovieSearchRepository;
import TestBHDStar.entity.MovieEntity;
import TestBHDStar.mapper.mapperImpl.MovieMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository

public class MovieRepositoryImpl implements MovieSearchRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Value("${MOVIE_PAGE_SIZE}")
    private  int MOVIE_PAGE_SIZE;

    @Transactional
    @Override
    public List<MovieDTO> searchAllByTitle(String title) {
        SearchSession searchSession = Search.session(entityManager);
        String queryString = title;
        List<MovieEntity> results = searchSession.search(MovieEntity.class)
                .where(f ->
                        f.match().field("title")
                                .matching(queryString).fuzzy(1))
                .fetchAll().hits();
        return MovieMapper.getInstance().toDTOList(results);
    }

    @Transactional
    public SearchResult<MovieEntity> searchByTitle(String title, int page) {
        SearchSession searchSession = Search.session(entityManager);
        String queryString = title;


        SearchResult<MovieEntity> results = searchSession.search(MovieEntity.class)
                .where(f ->
                                f.match().field("title")
                                        .matching(queryString).fuzzy(1))
                .fetch(page*MOVIE_PAGE_SIZE,MOVIE_PAGE_SIZE);


        return results;
    }
}
