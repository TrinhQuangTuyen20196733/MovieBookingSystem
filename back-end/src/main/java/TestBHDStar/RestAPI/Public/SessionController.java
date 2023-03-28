package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.Service.SessionService;
import TestBHDStar.entity.SessionEntity;
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
    @Autowired
    SessionService sessionService;
    @GetMapping("upcoming/{id}/sessions")
    public List<SessionDTO> findUpcomingByMovieId(@PathVariable int id,@RequestParam String  date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


            Date convertedDate = formatter.parse(date);
            return sessionService.getUpcomingMovie(id,convertedDate);


    }
}
