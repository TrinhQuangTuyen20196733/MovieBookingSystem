package TestBHDStar.service.Impl;

import TestBHDStar.DTO.SeatOnSessionDTO;
import TestBHDStar.Repository.SeatOnSessionRepository;
import TestBHDStar.service.SeatOnSessionService;
import TestBHDStar.entity.SeatOnSessionEntity;
import TestBHDStar.mapper.mapperImpl.SeatOnSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatOnSessionServiceImpl implements SeatOnSessionService {
    private final SeatOnSessionRepository seatOnSessionRepository;

    public SeatOnSessionServiceImpl(SeatOnSessionRepository seatOnSessionRepository) {
        this.seatOnSessionRepository = seatOnSessionRepository;
    }

    @Override
    public List<SeatOnSessionDTO> findAllBySessionId(int session_id) {
        List<SeatOnSessionEntity> sessionEntityList= seatOnSessionRepository.findAllBySessionId(session_id);
        return SeatOnSessionMapper.getInstance().toDTOList(sessionEntityList);
    }
}
