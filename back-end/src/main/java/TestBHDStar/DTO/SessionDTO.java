package TestBHDStar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private int cost;
    private int id;
    private MovieDTO movieDTO;
    private Date startTime;
    private RoomDTO roomDTO;
}
