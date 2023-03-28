package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.RoleDTO;
import TestBHDStar.DTO.ServiceDTO;
import TestBHDStar.entity.RoleEntity;
import TestBHDStar.entity.ServiceEntity;
import TestBHDStar.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper implements Mapper<ServiceEntity, ServiceDTO> {
    private static TestBHDStar.mapper.mapperImpl.ServiceMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.ServiceMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.ServiceMapper();
        }
        return INSTANCE;
    }
    @Override
    public ServiceEntity toEntity(ServiceDTO dto) {
        return null;
    }

    @Override
    public ServiceDTO toDTO(ServiceEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ServiceEntity, ServiceDTO> typeMap = modelMapper.createTypeMap(ServiceEntity.class, ServiceDTO.class);
        return modelMapper.map(entity, ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> toDTOList(List<ServiceEntity> entityList) {
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        entityList.forEach(serviceEntity -> {
            serviceDTOList.add(toDTO(serviceEntity));
        });
        return serviceDTOList;
    }

    @Override
    public List<ServiceEntity> toEntityList(List<ServiceDTO> dtoList) {
        return null;
    }
}
