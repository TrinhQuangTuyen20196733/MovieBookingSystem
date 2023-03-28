package TestBHDStar.Service;

import TestBHDStar.DTO.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAllByMovieSystemId(int id);
}
