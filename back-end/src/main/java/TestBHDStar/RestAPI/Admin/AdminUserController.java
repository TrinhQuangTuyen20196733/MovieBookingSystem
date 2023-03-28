package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.UserDTO;
import TestBHDStar.DTO.Page.UserPageDTO;
import TestBHDStar.DTO.ResponseMessage;
import TestBHDStar.Service.RoleService;
import TestBHDStar.Service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
      UserEntity userEntity = UserMapper.getInstance().toEntity(user);
      List<RoleEntity> roleEntityList = new ArrayList<>();
      userEntity.getAccount().getRoles().forEach(role->{
          roleEntityList.add(roleService.findByCode(role.getCode()).get());
      });
      userEntity.getAccount().setRoles(roleEntityList);

          UserDTO userDTO=  userService.save(userEntity);
          return  ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        UserEntity userEntity = UserMapper.getInstance().toEntity(user);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        userEntity.getAccount().getRoles().forEach(role->{
            roleEntityList.add(roleService.findByCode(role.getCode()).get());
        });
        userEntity.getAccount().setRoles(roleEntityList);
        userEntity .setId(id);
        UserDTO userDTO= userService.save(userEntity);
        return  ResponseEntity.status(HttpStatus.OK).body(userDTO);

    }
    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }
    @GetMapping("/users/pages/{pageNumber}")
    public UserPageDTO findAllByPage(@PathVariable int pageNumber) {
        return  userService.findPage(pageNumber);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findOne(@PathVariable int id){
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @GetMapping("/users/search/pages/{pageNumber}")
    public UserPageDTO searchByFullNameOrEmail(@RequestParam String fullNameOrEmail,@PathVariable int pageNumber){
        return userService.searchByFullNameOrEmail(fullNameOrEmail,pageNumber);
    }
    @DeleteMapping("/users/{id}")
    public  ResponseEntity<?>  deleteUser(@PathVariable @Positive int id){

            userService.delete(id);


        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Người dùng đã được xóa thành công!"));
    }
}
