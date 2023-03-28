package TestBHDStar.DTO.Page;

import TestBHDStar.DTO.UserDTO;
import TestBHDStar.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDTO extends BasePage {
    private List<UserDTO> userDTOList;
}
