package TestBHDStar.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompactSessionDTO {
    private int id;
    private int movie_id;
    private String startTime;
    private int movieSystem_id;
    private String roomName;
    private int cost;
}
