package TestBHDStar.RestAPI.Public;

import TestBHDStar.DTO.ServiceDTO;
import TestBHDStar.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    @GetMapping("/services")
    public List<ServiceDTO> findAll() {
        return  serviceService.findAll();
    }
}
