package TestBHDStar.Service.Impl;

import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.Repository.RoomRepository;
import TestBHDStar.Service.RoomService;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.mapper.mapperImpl.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Override
    public List<RoomDTO> findAllByMovieSystemId(int id) {
        List<RoomEntity> roomEntityList = roomRepository.findByMovieSystem_Id(id);
        return RoomMapper.getInstance().toDTOList(roomEntityList);
    }
}
