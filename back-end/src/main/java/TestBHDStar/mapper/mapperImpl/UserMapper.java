package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.AccountDTO;
import TestBHDStar.DTO.RoleDTO;
import TestBHDStar.DTO.UserDTO;
import TestBHDStar.entity.AccountEntity;
import TestBHDStar.entity.RoleEntity;
import TestBHDStar.entity.UserEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class UserMapper implements Mapper<UserEntity, UserDTO> {
    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }

    @Override
    public UserEntity toEntity(UserDTO dto) {
        AccountDTO accountDTO = dto.getAccountDTO();
        List<String> roles = accountDTO.getRoles();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roles.forEach(role->{
            roleEntityList.add(RoleMapper.getInstance().toEntity(new RoleDTO(role)));
        });
        ModelMapper modelMapper = new ModelMapper();
        AccountEntity accountEntity = AccountMapper.getInstance().toEntity(accountDTO);
        accountEntity.setRoles(roleEntityList);
        TypeMap<UserDTO, UserEntity> typeMap = modelMapper.createTypeMap(UserDTO.class, UserEntity.class);

        UserEntity user = modelMapper.map(dto,UserEntity.class);
        user.setAccount(accountEntity);
        return user;
    }

    @Override
    public UserDTO toDTO(UserEntity entity) {
        AccountDTO account = AccountMapper.getInstance().toDTO(entity.getAccount());
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserEntity, UserDTO> typeMap = modelMapper.createTypeMap(UserEntity.class, UserDTO.class);
        typeMap.addMappings(mapping -> mapping.map(src -> account, UserDTO::setAccountDTO));
        return modelMapper.map(entity,UserDTO.class);
    }

    @Override
    public List<UserDTO> toDTOList(List<UserEntity> entityList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        entityList.forEach(entity -> {
            UserDTO userDTO = toDTO(entity);
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserDTO> dtoList) {
        return null;
    }
}
