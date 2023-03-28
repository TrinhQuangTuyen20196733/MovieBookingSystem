package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.SeatOnSessionDTO;
import TestBHDStar.Service.SeatOnSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatOnSessionController {
    @Autowired
    SeatOnSessionService seatOnSessionService;
    @GetMapping("/sessions/{session_id}/seatOnSessions")
    public List<SeatOnSessionDTO> findAllBySessionId(@PathVariable int session_id) {
        return  seatOnSessionService.findAllBySessionId(session_id);
    }
}
