package TestBHDStar.DTO.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage {
    private long totalPage;
    private long totalItemPage;
}
