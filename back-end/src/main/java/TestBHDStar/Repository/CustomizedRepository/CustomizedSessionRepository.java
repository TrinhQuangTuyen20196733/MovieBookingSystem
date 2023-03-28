package TestBHDStar.Repository.CustomizedRepository;

import TestBHDStar.entity.SessionEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomizedSessionRepository {
    List<SessionEntity> findAllByMovieId(int id);
    List<SessionEntity> getUpcomingMovie(int id,Date date);
}
