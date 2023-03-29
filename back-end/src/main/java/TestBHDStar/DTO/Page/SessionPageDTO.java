package TestBHDStar.DTO.Page;

import TestBHDStar.DTO.SessionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionPageDTO extends BasePage{
    private List<SessionDTO> sessionDTOList;
}
