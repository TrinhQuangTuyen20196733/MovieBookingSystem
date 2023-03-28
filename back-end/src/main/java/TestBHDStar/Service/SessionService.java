package TestBHDStar.Service;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.entity.SessionEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SessionService {
    List<SessionDTO> findAllByMovieId(int id);
    List<SessionDTO> getUpcomingMovie(int id,Date date);
    SessionDTO createSession(CompactSessionDTO compactSessionDTO) throws ParseException;
}
