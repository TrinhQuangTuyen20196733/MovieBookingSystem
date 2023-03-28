package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.SeatOnSessionDTO;
import TestBHDStar.entity.SeatOnSessionEntity;
import TestBHDStar.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

public class SeatOnSessionMapper implements Mapper<SeatOnSessionEntity, SeatOnSessionDTO> {
    private static TestBHDStar.mapper.mapperImpl.SeatOnSessionMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.SeatOnSessionMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.SeatOnSessionMapper();
        }
        return INSTANCE;
    }
    @Override
    public SeatOnSessionEntity toEntity(SeatOnSessionDTO dto) {
        return null;
    }

    @Override
    public SeatOnSessionDTO toDTO(SeatOnSessionEntity entity) {
        SeatOnSessionDTO seatOnSessionDTO = new SeatOnSessionDTO();
        seatOnSessionDTO.setId(entity.getId());
        seatOnSessionDTO.setStatus(entity.isStatus());
        seatOnSessionDTO.setSeatDTO(SeatMapper.getInstance().toDTO(entity.getSeat()));
        seatOnSessionDTO.setSession_id(entity.getSession().getId());
        return seatOnSessionDTO;
    }

    @Override
    public List<SeatOnSessionDTO> toDTOList(List<SeatOnSessionEntity> entityList) {
        List<SeatOnSessionDTO> sessionDTOList = new ArrayList<>();
        entityList.forEach(seatOnSessionEntity -> {
            sessionDTOList.add(toDTO(seatOnSessionEntity));
        });
        return sessionDTOList;
    }

    @Override
    public List<SeatOnSessionEntity> toEntityList(List<SeatOnSessionDTO> dtoList) {
        return null;
    }
}
