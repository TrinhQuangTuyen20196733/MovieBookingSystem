package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.entity.MovieEntity;
import TestBHDStar.mapper.Mapper;
import TestBHDStar.utils.ThumbnailFileUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper implements Mapper<MovieEntity, MovieDTO> {
    private static TestBHDStar.mapper.mapperImpl.MovieMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.MovieMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.MovieMapper();
        }
        return INSTANCE;
    }
    @Override
    public MovieEntity toEntity(MovieDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MovieDTO, MovieEntity> typeMap = modelMapper.createTypeMap(MovieDTO.class, MovieEntity.class);
        return modelMapper.map(dto,MovieEntity.class);
    }

    @Override
    public MovieDTO toDTO(MovieEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MovieEntity, MovieDTO> typeMap = modelMapper.createTypeMap(MovieEntity.class, MovieDTO.class);
        byte[] thumbnailbytes = ThumbnailFileUtil.getInstance().getThumbnail(entity.getThumbnail());
        typeMap.addMappings(mapping->mapping.map(src->thumbnailbytes, MovieDTO::setThumbnail));
        return modelMapper.map(entity,MovieDTO.class);
    }

    @Override
    public List<MovieDTO> toDTOList(List<MovieEntity> entityList) {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        entityList.forEach(entity -> {
            MovieDTO movieDTO = toDTO(entity);
            movieDTOList.add(movieDTO);
        });
        return movieDTOList;
    }

    @Override
    public List<MovieEntity> toEntityList(List<MovieDTO> dtoList) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        dtoList.forEach(movieDTO -> {
            movieEntityList.add(toEntity(movieDTO));
        });

        return movieEntityList;
    }
}
