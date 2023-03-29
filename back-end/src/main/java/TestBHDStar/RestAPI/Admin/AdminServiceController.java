package TestBHDStar.RestAPI.Admin;

import TestBHDStar.DTO.Page.ServicePageDTO;
import TestBHDStar.DTO.ResponseMessage;
import TestBHDStar.DTO.ServiceDTO;
import TestBHDStar.entity.ServiceEntity;
import TestBHDStar.service.ServiceService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminServiceController {
    private final ServiceService serviceService;

    public AdminServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services/pages/{pageNumber}")
    public ServicePageDTO findServiceByPage(@PathVariable @Positive int pageNumber) {
        return serviceService.findServiceByPage(pageNumber);
    }
    @PostMapping("/services")
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
       return  serviceService.createService(serviceDTO);
    }
    @PutMapping("/services/{id}")
    public ServiceDTO updateService(@PathVariable @Positive int id,@RequestBody ServiceDTO serviceDTO) {
        serviceDTO.setId(id);
        return serviceService.createService(serviceDTO);
    }
    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteService(@PathVariable @Positive int id){

        serviceService.deleteService(id);


        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Dịch vụ đã được xóa thành công!"));
    }
}
