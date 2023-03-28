package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/admin")
public class AdminSessionController {
    @Autowired
    SessionService sessionService;

    @PostMapping("/sessions")
    public SessionDTO createSession(@RequestBody CompactSessionDTO compactSessionDTO) throws ParseException {
  return  sessionService.createSession(compactSessionDTO);
    }
}
