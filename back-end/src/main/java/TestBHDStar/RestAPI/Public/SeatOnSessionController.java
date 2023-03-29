package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.SeatOnSessionDTO;
import TestBHDStar.service.SeatOnSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatOnSessionController {
    private final SeatOnSessionService seatOnSessionService;

    public SeatOnSessionController(SeatOnSessionService seatOnSessionService) {
        this.seatOnSessionService = seatOnSessionService;
    }

    @GetMapping("/sessions/{session_id}/seatOnSessions")
    public List<SeatOnSessionDTO> findAllSeatOnSessionsBySessionId(@PathVariable int session_id) {
        return  seatOnSessionService.findAllBySessionId(session_id);
    }
}
