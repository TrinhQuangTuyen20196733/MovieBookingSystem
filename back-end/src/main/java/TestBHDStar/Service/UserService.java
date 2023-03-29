package TestBHDStar.service;

import TestBHDStar.DTO.Page.UserPageDTO;
import TestBHDStar.DTO.UserDTO;
import TestBHDStar.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserEntity userEntity);
    List<UserDTO> findAll();
    UserDTO findUserById(int id) ;
    UserPageDTO findUserByPage(int pageNumber);
    UserPageDTO searchUserByFullNameOrEmail(String fullNameOrEmail, int page);
    void deleteUser(int id);
}
