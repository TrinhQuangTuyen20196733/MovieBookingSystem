package TestBHDStar.Repository;

import TestBHDStar.Repository.CustomizedRepository.CustomizedSessionRepository;
import TestBHDStar.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Integer>, CustomizedSessionRepository {

}
