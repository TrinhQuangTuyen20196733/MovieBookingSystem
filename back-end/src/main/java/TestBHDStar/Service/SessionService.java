package TestBHDStar.service;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.Page.SessionPageDTO;
import TestBHDStar.DTO.SessionDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SessionService {
    List<SessionDTO> findAllSessionByMovieId(int id);
    List<SessionDTO> getUpcomingMovie(int id,Date date);
    SessionDTO createSession(CompactSessionDTO compactSessionDTO) throws ParseException;
    SessionDTO findSessionById(int id);
    SessionPageDTO findSessionByPage(int pageNumber);

    void deleteSession(int id);
}
