package TestBHDStar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private UserDTO userDTO;
    private List<SeatOnSessionDTO> seatOnSessionDTOList;
    private List<ServiceReceiptDTO> serviceReceiptDTOList;
}
