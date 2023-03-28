package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.AccountDTO;
import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.Repository.MovieSystemRepository;
import TestBHDStar.entity.AccountEntity;
import TestBHDStar.entity.MovieSystemEntity;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper implements Mapper<RoomEntity, RoomDTO> {
    @Autowired
    MovieSystemRepository movieSystemRepository;
    private static TestBHDStar.mapper.mapperImpl.RoomMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.RoomMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.RoomMapper();
        }
        return INSTANCE;
    }
    @Override
    public RoomEntity toEntity(RoomDTO dto) {
        MovieSystemDTO movieSystemDTO = dto.getMovieSystemDTO();
        MovieSystemEntity movieSystemEntity = movieSystemRepository.findById(movieSystemDTO.getId()).get();
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<RoomDTO, RoomEntity> typeMap =  modelMapper.createTypeMap(RoomDTO.class,RoomEntity.class);
        RoomEntity roomEntity=  modelMapper.map(dto,RoomEntity.class);
        roomEntity.setMovieSystem(movieSystemEntity);
        return roomEntity;
    }

    @Override
    public RoomDTO toDTO(RoomEntity entity) {
        MovieSystemEntity movieSystemEntity = entity.getMovieSystem();

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<RoomEntity, RoomDTO> typeMap =  modelMapper.createTypeMap(RoomEntity.class,RoomDTO.class);
        RoomDTO roomDTO=  modelMapper.map(entity,RoomDTO.class);
        roomDTO.setMovieSystemDTO(MovieSystemMapper.getInstance().toDTO(movieSystemEntity));
        return roomDTO;
    }

    @Override
    public List<RoomDTO> toDTOList(List<RoomEntity> entityList) {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        entityList.forEach(roomEntity -> {
            roomDTOList.add(toDTO(roomEntity));
        });
        return roomDTOList;
    }

    @Override
    public List<RoomEntity> toEntityList(List<RoomDTO> dtoList) {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        dtoList.forEach(roomDTO -> {
            roomEntityList.add(toEntity(roomDTO));
        });
        return roomEntityList;
    }
}
