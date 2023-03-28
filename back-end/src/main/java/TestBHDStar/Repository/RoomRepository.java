package TestBHDStar.Repository;

import TestBHDStar.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    List<RoomEntity> findByMovieSystem_Id(int id);

    RoomEntity findByMovieSystem_IdAndName(int movieSystem_id, String roomName);
}
