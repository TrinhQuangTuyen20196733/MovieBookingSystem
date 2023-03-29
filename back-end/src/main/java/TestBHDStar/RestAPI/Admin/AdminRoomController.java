package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.RoomDTO;
import TestBHDStar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRoomController {
    private final RoomService roomService;

    public AdminRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("movieSystems/{id}/rooms")
    public List<RoomDTO>  findAllRoomByMovieSystemId(@PathVariable int id) {
        return  roomService.findAllRoomByMovieSystemId(id);
    }
}
