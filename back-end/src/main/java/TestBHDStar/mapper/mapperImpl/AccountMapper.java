package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.AccountDTO;
import TestBHDStar.DTO.RoleDTO;
import TestBHDStar.entity.AccountEntity;
import TestBHDStar.entity.RoleEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper implements Mapper<AccountEntity, AccountDTO> {
    private static TestBHDStar.mapper.mapperImpl.AccountMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.AccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.AccountMapper();
        }
        return INSTANCE;
    }
    @Override
    public AccountEntity toEntity(AccountDTO dto) {
       List<RoleEntity> roleEntityList = new ArrayList<>();
       dto.getRoles().forEach(role->{
           roleEntityList.add(RoleMapper.getInstance().toEntity(new RoleDTO(role)));
       });
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String accountPassword = passwordEncoder.encode(dto.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<AccountDTO,AccountEntity> typeMap =  modelMapper.createTypeMap(AccountDTO.class,AccountEntity.class);
        typeMap.addMappings(mapping->mapping.map(src->roleEntityList,AccountEntity::setRoles));
        typeMap.addMappings(mapping->mapping.map(src->accountPassword,AccountEntity::setPassword));
        return modelMapper.map(dto,AccountEntity.class);
    }

    @Override
    public AccountDTO toDTO(AccountEntity entity) {
        List<String> roles = new ArrayList<>();
        entity.getRoles().forEach(roleEntity -> {
            roles.add(roleEntity.getCode());
        } );
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<AccountEntity,AccountDTO> typeMap =  modelMapper.createTypeMap(AccountEntity.class,AccountDTO.class);
        typeMap.addMappings(mapping -> mapping.map(src -> roles,AccountDTO::setRoles));
        return  modelMapper.map(entity,AccountDTO.class);
    }

    @Override
    public List<AccountDTO> toDTOList(List<AccountEntity> entityList) {

        return null;
    }

    @Override
    public List<AccountEntity> toEntityList(List<AccountDTO> dtoList) {
        return null;
    }
}
