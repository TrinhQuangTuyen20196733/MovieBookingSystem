package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("movies/{id}/upcoming/sessions")
    public List<SessionDTO> findUpcomingSessionByMovieId(@PathVariable int id,@RequestParam String  date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


            Date convertedDate = formatter.parse(date);
            return sessionService.getUpcomingMovie(id,convertedDate);


    }
    @GetMapping("/sessions/{id}")
    public SessionDTO findById(@PathVariable int id) {
        return  sessionService.findSessionById(id);
    }
}
