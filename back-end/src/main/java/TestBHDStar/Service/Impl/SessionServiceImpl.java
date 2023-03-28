package TestBHDStar.Service.Impl;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.Repository.MovieRepository;
import TestBHDStar.Repository.RoomRepository;
import TestBHDStar.Repository.SessionRepository;
import TestBHDStar.Service.SessionService;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.entity.SeatOnSessionEntity;
import TestBHDStar.entity.SessionEntity;
import TestBHDStar.mapper.mapperImpl.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    RoomRepository roomRepository;


    @Override
    public List<SessionDTO> findAllByMovieId(int id) {

        List<SessionEntity> session =sessionRepository.findAllByMovieId(id);
        return SessionMapper.getInstance().toDTOList(session);
    }

    @Override
    public List<SessionDTO> getUpcomingMovie(int id, Date date) {
        List<SessionEntity> session =sessionRepository.getUpcomingMovie(id,date);
        return SessionMapper.getInstance().toDTOList(session);
    }

    @Override
    public SessionDTO createSession(CompactSessionDTO dto) throws ParseException {
        SessionEntity session = new SessionEntity();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        session.setId(dto.getId());
        session.setStartTime(formatter.parse(dto.getStartTime()));
        session.setMovie(movieRepository.findById(dto.getMovie_id()).get());
        session.setCost(dto.getCost());
        RoomEntity roomEntity = roomRepository.findByMovieSystem_IdAndName(dto.getMovieSystem_id(),dto.getRoomName());
        session.setRoom(roomEntity);
        List<SeatOnSessionEntity> seatOnSessionEntityList = new ArrayList<>();
        roomEntity.getSeatsList().forEach(seatEntity -> {
            SeatOnSessionEntity seatOnSessionEntity = new SeatOnSessionEntity();
            seatOnSessionEntity.setSeat(seatEntity);
            seatOnSessionEntity.setStatus(false);
            seatOnSessionEntity.setSession(session);
            seatOnSessionEntityList.add(seatOnSessionEntity);

        });
        session.setSeatOnSessionEntityList(seatOnSessionEntityList);
        SessionEntity sessionEntity = sessionRepository.save(session);

        return  SessionMapper.getInstance().toDTO(sessionEntity);
    }
}
