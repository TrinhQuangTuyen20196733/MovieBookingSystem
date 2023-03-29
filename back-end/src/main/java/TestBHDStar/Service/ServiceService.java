package TestBHDStar.service;

import TestBHDStar.DTO.Page.ServicePageDTO;
import TestBHDStar.DTO.ServiceDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> findAll();

    ServicePageDTO findServiceByPage(int pageNumber);

    ServiceDTO createService(ServiceDTO serviceDTO);

    void deleteService(int id);
}
