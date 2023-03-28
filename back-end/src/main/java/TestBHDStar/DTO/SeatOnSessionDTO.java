package TestBHDStar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatOnSessionDTO {
    private int id;
    private SeatDTO seatDTO;
    private int session_id;
    private boolean status;
}
