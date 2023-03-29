package TestBHDStar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReceiptDTO {
    private int id;
    private int service_id;
    private int receipt_id;
    private int count;
}
