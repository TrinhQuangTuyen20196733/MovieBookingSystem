package TestBHDStar.Service;

import TestBHDStar.DTO.Page.UserPageDTO;
import TestBHDStar.DTO.UserDTO;
import TestBHDStar.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    UserDTO save(UserEntity userEntity);
    List<UserDTO> findAll();
    UserDTO findById(int id) ;
    UserPageDTO findPage(int pageNumber);
    UserPageDTO searchByFullNameOrEmail(String fullNameOrEmail, int page);
    void delete(int id);
}
