package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.MovieDTO;
import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.entity.SessionEntity;
import TestBHDStar.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionMapper implements Mapper<SessionEntity, SessionDTO> {

    private static TestBHDStar.mapper.mapperImpl.SessionMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.SessionMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.SessionMapper();
        }
        return INSTANCE;
    }
    @Override
    public SessionEntity toEntity(SessionDTO dto) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(dto.getId());
        sessionEntity.setMovie(MovieMapper.getInstance().toEntity(dto.getMovieDTO()));
        sessionEntity.setRoom(RoomMapper.getInstance().toEntity(dto.getRoomDTO()));
        sessionEntity.setStartTime(dto.getStartTime());
        sessionEntity.setCost(dto.getCost());
        return sessionEntity;
    }

    @Override
    public SessionDTO toDTO(SessionEntity entity) {
        MovieDTO movieDTO = MovieMapper.getInstance().toDTO(entity.getMovie());
        RoomDTO roomDTO = RoomMapper.getInstance().toDTO(entity.getRoom());
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(entity.getId());
        sessionDTO.setRoomDTO(roomDTO);
        sessionDTO.setMovieDTO(movieDTO);
        sessionDTO.setStartTime(entity.getStartTime());
        sessionDTO.setCost(entity.getCost());
        return sessionDTO;
    }

    @Override
    public List<SessionDTO> toDTOList(List<SessionEntity> entityList) {
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        entityList.forEach(sessionEntity -> {
            sessionDTOList.add(toDTO(sessionEntity));
        });
        return sessionDTOList;
    }

    @Override
    public List<SessionEntity> toEntityList(List<SessionDTO> dtoList) {
        List<SessionEntity> sessionEntitieList = new ArrayList<>();
        dtoList.forEach(sessionDTO -> {
            sessionEntitieList.add(toEntity(sessionDTO));
        });
        return sessionEntitieList;
    }
}
