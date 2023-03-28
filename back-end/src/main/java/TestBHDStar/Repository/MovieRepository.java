package TestBHDStar.Repository;

import TestBHDStar.Repository.CustomizedRepository.MovieSearchRepository;
import TestBHDStar.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer>, MovieSearchRepository {
}
