package TestBHDStar.service.Impl;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.Page.MoviePageDTO;
import TestBHDStar.DTO.Page.SessionPageDTO;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.Repository.MovieRepository;
import TestBHDStar.Repository.RoomRepository;
import TestBHDStar.Repository.SessionRepository;
import TestBHDStar.entity.MovieEntity;
import TestBHDStar.exception.UserNotFoundException;
import TestBHDStar.mapper.mapperImpl.MovieMapper;
import TestBHDStar.service.SessionService;
import TestBHDStar.entity.RoomEntity;
import TestBHDStar.entity.SeatOnSessionEntity;
import TestBHDStar.entity.SessionEntity;
import TestBHDStar.mapper.mapperImpl.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Value("${SESSION_PAGE_SIZE}")
    private int SESSION_PAGE_SIZE;
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public List<SessionDTO> findAllSessionByMovieId(int id) {

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

    @Override
    public SessionDTO findSessionById(int id) {
        SessionEntity session = sessionRepository.findById(id).get();
        return SessionMapper.getInstance().toDTO(session);
    }

    @Override
    public SessionPageDTO findSessionByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SESSION_PAGE_SIZE);
        Page<SessionEntity> sessionEntityPage = sessionRepository.findAll(pageable);
        SessionPageDTO sessionPageDTO = new SessionPageDTO();
        sessionPageDTO.setSessionDTOList(SessionMapper.getInstance().toDTOList(sessionEntityPage.getContent()));
        sessionPageDTO.setTotalItemPage(sessionEntityPage.getTotalElements());
        sessionPageDTO.setTotalPage(sessionEntityPage.getTotalPages());
        return sessionPageDTO;
    }

    @Override
    public void deleteSession(int id) {

        try{
            sessionRepository.deleteById(id);
        }
        catch (Exception e) {
            throw  new UserNotFoundException("Suất chiếu bạn muốn xóa không tồn tại");
        }
    }
}
