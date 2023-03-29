package TestBHDStar.service;

import TestBHDStar.DTO.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAllRoomByMovieSystemId(int id);
}
