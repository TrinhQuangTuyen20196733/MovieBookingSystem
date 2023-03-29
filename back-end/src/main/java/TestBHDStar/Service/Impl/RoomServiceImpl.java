package TestBHDStar.service.Impl;

import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.Repository.RoomRepository;
import TestBHDStar.service.RoomService;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.mapper.mapperImpl.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> findAllRoomByMovieSystemId(int id) {
        List<RoomEntity> roomEntityList = roomRepository.findByMovieSystem_Id(id);
        return RoomMapper.getInstance().toDTOList(roomEntityList);
    }
}
