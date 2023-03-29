package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.CompactSessionDTO;
import TestBHDStar.DTO.Page.SessionPageDTO;
import TestBHDStar.DTO.ResponseMessage;
import TestBHDStar.DTO.SessionDTO;
import TestBHDStar.service.SessionService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminSessionController {
    private final SessionService sessionService;

    public AdminSessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/sessions")
    public SessionDTO createSession(@RequestBody CompactSessionDTO compactSessionDTO) throws ParseException {
  return  sessionService.createSession(compactSessionDTO);
    }
    @GetMapping("/sessions/pages/{pageNumber}")
    public SessionPageDTO findSessionByPage(@PathVariable @Positive  int pageNumber){
        return  sessionService.findSessionByPage(pageNumber);
    }
    @DeleteMapping("/sessions/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable @Positive int id){

        sessionService.deleteSession(id);


        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Người dùng đã được xóa thành công!"));
    }
}
