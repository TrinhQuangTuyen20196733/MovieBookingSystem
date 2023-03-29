package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.UserDTO;
import TestBHDStar.DTO.Page.UserPageDTO;
import TestBHDStar.DTO.ResponseMessage;
import TestBHDStar.service.RoleService;
import TestBHDStar.service.UserService;
import TestBHDStar.entity.RoleEntity;
import TestBHDStar.entity.UserEntity;
import TestBHDStar.mapper.mapperImpl.UserMapper;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminUserController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
      UserEntity userEntity = UserMapper.getInstance().toEntity(user);
      List<RoleEntity> roleEntityList = new ArrayList<>();
      userEntity.getAccount().getRoles().forEach(role->{
          roleEntityList.add(roleService.findRoleByCode(role.getCode()).get());
      });
      userEntity.getAccount().setRoles(roleEntityList);

          UserDTO userDTO=  userService.createUser(userEntity);
          return  ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        UserEntity userEntity = UserMapper.getInstance().toEntity(user);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        userEntity.getAccount().getRoles().forEach(role->{
            roleEntityList.add(roleService.findRoleByCode(role.getCode()).get());
        });
        userEntity.getAccount().setRoles(roleEntityList);
        userEntity .setId(id);
        UserDTO userDTO= userService.createUser(userEntity);
        return  ResponseEntity.status(HttpStatus.OK).body(userDTO);

    }
    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }
    @GetMapping("/users/pages/{pageNumber}")
    public UserPageDTO findAllUserByPage(@PathVariable int pageNumber) {
        return  userService.findUserByPage(pageNumber);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable int id){
        UserDTO userDTO = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @GetMapping("/users/search/pages/{pageNumber}")
    public UserPageDTO searchUserByFullNameOrEmail(@RequestParam String fullNameOrEmail,@PathVariable int pageNumber){
        return userService.searchUserByFullNameOrEmail(fullNameOrEmail,pageNumber);
    }
    @DeleteMapping("/users/{id}")
    public  ResponseEntity<?>  deleteUser(@PathVariable @Positive int id){

            userService.deleteUser(id);


        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Người dùng đã được xóa thành công!"));
    }
}
