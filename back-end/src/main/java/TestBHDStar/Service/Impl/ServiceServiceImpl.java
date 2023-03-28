package TestBHDStar.Service.Impl;

import TestBHDStar.DTO.ServiceDTO;
import TestBHDStar.Repository.ServiceRepository;
import TestBHDStar.Service.ServiceService;
import TestBHDStar.entity.ServiceEntity;
import TestBHDStar.mapper.mapperImpl.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Override
    public List<ServiceDTO> findAll() {
        List<ServiceEntity> serviceEntities= serviceRepository.findAll();
        return ServiceMapper.getInstance().toDTOList(serviceEntities);
    }
}
