package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.entity.MovieSystemEntity;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class MovieSystemMapper implements Mapper<MovieSystemEntity, MovieSystemDTO> {
    private static TestBHDStar.mapper.mapperImpl.MovieSystemMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.MovieSystemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.MovieSystemMapper();
        }
        return INSTANCE;
    }
    @Override
    public MovieSystemEntity toEntity(MovieSystemDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MovieSystemDTO, MovieSystemEntity> typeMap =  modelMapper.createTypeMap(MovieSystemDTO.class,MovieSystemEntity.class);
        return  modelMapper.map(dto,MovieSystemEntity.class);
    }

    @Override
    public MovieSystemDTO toDTO(MovieSystemEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MovieSystemEntity, MovieSystemDTO> typeMap =  modelMapper.createTypeMap(MovieSystemEntity.class,MovieSystemDTO.class);
        return  modelMapper.map(entity,MovieSystemDTO.class);
    }

    @Override
    public List<MovieSystemDTO> toDTOList(List<MovieSystemEntity> entityList) {
        List<MovieSystemDTO> movieSystemDTOList = new ArrayList<>();
        entityList.forEach(movieSystem->{
            movieSystemDTOList.add(toDTO(movieSystem));
        });
        return movieSystemDTOList;
    }

    @Override
    public List<MovieSystemEntity> toEntityList(List<MovieSystemDTO> dtoList) {
        List<MovieSystemEntity> movieSystemEntitieList = new ArrayList<>();
        dtoList.forEach(movieSystemDTO -> {
            movieSystemEntitieList.add(toEntity(movieSystemDTO));
        });
        return movieSystemEntitieList;
    }
}
