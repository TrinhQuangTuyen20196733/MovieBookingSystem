package TestBHDStar.Repository.Impl;

import TestBHDStar.Repository.CustomizedRepository.CustomizedSessionRepository;
import TestBHDStar.entity.SessionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class SessionRepositoryImpl implements CustomizedSessionRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<SessionEntity> findAllByMovieId(int id) {
        String hql = "  FROM SessionEntity as session  WHERE session.movie.id=:id" ;
        TypedQuery<SessionEntity> query = entityManager.createQuery(hql, SessionEntity.class);
        query.setParameter("id",id);
        List<SessionEntity> sessionEntityList = query.getResultList();
        return sessionEntityList;
    }

    @Override
    public List<SessionEntity> getUpcomingMovie(int id, Date date  ) {
        String hql = "FROM SessionEntity as session WHERE session.movie.id = :id AND session.startTime >= :startTime  ";
        TypedQuery<SessionEntity> query = entityManager.createQuery(hql, SessionEntity.class);
        query.setParameter("id", id);
        query.setParameter("startTime", date);
        List<SessionEntity> sessionEntityList = query.getResultList();
        return sessionEntityList;
    }


}
