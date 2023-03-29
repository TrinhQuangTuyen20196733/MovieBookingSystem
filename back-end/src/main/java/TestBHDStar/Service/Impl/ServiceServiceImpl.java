package TestBHDStar.service.Impl;

import TestBHDStar.DTO.Page.ServicePageDTO;
import TestBHDStar.DTO.Page.SessionPageDTO;
import TestBHDStar.DTO.ServiceDTO;
import TestBHDStar.Repository.ServiceRepository;
import TestBHDStar.entity.SessionEntity;
import TestBHDStar.exception.UserNotFoundException;
import TestBHDStar.mapper.mapperImpl.SessionMapper;
import TestBHDStar.service.ServiceService;
import TestBHDStar.entity.ServiceEntity;
import TestBHDStar.mapper.mapperImpl.ServiceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Value("${SERVICE_PAGE_SIZE}")
    private int SERVICE_PAGE_SIZE;
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDTO> findAll() {
        List<ServiceEntity> serviceEntities= serviceRepository.findAll();
        return ServiceMapper.getInstance().toDTOList(serviceEntities);
    }

    @Override
    public ServicePageDTO findServiceByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SERVICE_PAGE_SIZE);
        Page<ServiceEntity> serviceEntityPage = serviceRepository.findAll(pageable);
        ServicePageDTO servicePageDTO = new ServicePageDTO();
        servicePageDTO.setServiceDTOList(ServiceMapper.getInstance().toDTOList(serviceEntityPage.getContent()));
        servicePageDTO.setTotalItemPage(serviceEntityPage.getTotalElements());
        servicePageDTO.setTotalPage(serviceEntityPage.getTotalPages());
        return servicePageDTO;
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        ServiceEntity serviceEntity = ServiceMapper.getInstance().toEntity(serviceDTO);
        serviceEntity = serviceRepository.save(serviceEntity);
        return ServiceMapper.getInstance().toDTO(serviceEntity);
    }

    @Override
    public void deleteService(int id) {

        try{
            serviceRepository.deleteById(id);
        }
        catch (Exception e) {
            throw  new UserNotFoundException("Dịch vụ bạn muốn xóa không tồn tại");
        }
    }
}
