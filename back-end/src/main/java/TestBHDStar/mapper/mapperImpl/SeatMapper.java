package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.AccountDTO;
import TestBHDStar.DTO.SeatDTO;
import TestBHDStar.entity.AccountEntity;
import TestBHDStar.entity.SeatEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class SeatMapper implements Mapper<SeatEntity, SeatDTO> {
    private static TestBHDStar.mapper.mapperImpl.SeatMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.SeatMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.SeatMapper();
        }
        return INSTANCE;
    }

    @Override
    public SeatEntity toEntity(SeatDTO dto) {
        return null;
    }

    @Override
    public SeatDTO toDTO(SeatEntity entity) {
        int room_id = entity.getRoom().getId();
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<SeatEntity, SeatDTO> typeMap =  modelMapper.createTypeMap(SeatEntity.class,SeatDTO.class);
        typeMap.addMappings(mapping->mapping.map(src->room_id,SeatDTO::setRoom_id));

        return modelMapper.map(entity,SeatDTO.class);
    }

    @Override
    public List<SeatDTO> toDTOList(List<SeatEntity> entityList) {
        List<SeatDTO> seatDTOList = new ArrayList<>();
        entityList.forEach(seatEntity -> {
            seatDTOList.add(toDTO(seatEntity));
        });
        return seatDTOList;
    }

    @Override
    public List<SeatEntity> toEntityList(List<SeatDTO> dtoList) {
        return null;
    }
}
