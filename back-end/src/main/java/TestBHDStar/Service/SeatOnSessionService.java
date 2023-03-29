package TestBHDStar.service;

import TestBHDStar.DTO.SeatOnSessionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatOnSessionService {
    List<SeatOnSessionDTO> findAllBySessionId(int session_id);
}
