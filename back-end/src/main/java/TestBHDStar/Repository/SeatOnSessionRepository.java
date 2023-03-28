package TestBHDStar.Repository;

import TestBHDStar.entity.SeatOnSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatOnSessionRepository extends JpaRepository<SeatOnSessionEntity,Integer>{

    List<SeatOnSessionEntity> findAllBySessionId(int session_id);
}
