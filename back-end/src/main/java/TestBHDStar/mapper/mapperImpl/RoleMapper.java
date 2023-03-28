package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.RoleDTO;
import TestBHDStar.entity.RoleEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
public class RoleMapper implements Mapper<RoleEntity, RoleDTO> {


    private static TestBHDStar.mapper.mapperImpl.RoleMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.RoleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.RoleMapper();
        }
        return INSTANCE;
    }

    @Override
    public RoleEntity toEntity(RoleDTO dto) {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode(dto.getCode());
        return roleEntity;

    }

    @Override
    public RoleDTO toDTO(RoleEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<RoleEntity, RoleDTO> typeMap = modelMapper.createTypeMap(RoleEntity.class, RoleDTO.class);
        typeMap.addMappings(mapping -> mapping.map(src -> src.getCode(), RoleDTO::setCode));
        return modelMapper.map(entity, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> toDTOList(List<RoleEntity> entityList) {
        List<RoleDTO> dtoList = new ArrayList<>();
        entityList.forEach(role->{
            dtoList.add(toDTO(role));
        });
        return dtoList;
    }

    @Override
    public List<RoleEntity> toEntityList(List<RoleDTO> dtoList) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        dtoList.forEach(role->{
            roleEntityList.add(toEntity(role));
        });
        return roleEntityList;
    }
}
