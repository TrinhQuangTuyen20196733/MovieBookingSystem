package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.ServiceReceiptDTO;
import TestBHDStar.entity.ServiceReceipt;
import TestBHDStar.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ServiceReceiptMapper implements Mapper<ServiceReceipt,ServiceReceiptDTO> {
    private static TestBHDStar.mapper.mapperImpl.ServiceReceiptMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.ServiceReceiptMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.ServiceReceiptMapper();
        }
        return INSTANCE;
    }
    @Override
    public ServiceReceipt toEntity(ServiceReceiptDTO dto) {
        return null;
    }

    @Override
    public ServiceReceiptDTO toDTO(ServiceReceipt entity) {
        ServiceReceiptDTO serviceReceiptDTO = new ServiceReceiptDTO();
        serviceReceiptDTO.setId(entity.getId());
        serviceReceiptDTO.setReceipt_id(entity.getReceipt().getId());
        serviceReceiptDTO.setService_id(entity.getServiceEntity().getId());
        serviceReceiptDTO.setCount(entity.getCount());
        return serviceReceiptDTO;
    }

    @Override
    public List<ServiceReceiptDTO> toDTOList(List<ServiceReceipt> entityList) {
        List<ServiceReceiptDTO> serviceReceiptDTOList = new ArrayList<>();
        entityList.forEach(serviceReceipt -> {
            serviceReceiptDTOList.add(toDTO(serviceReceipt));
        });
        return serviceReceiptDTOList;
    }

    @Override
    public List<ServiceReceipt> toEntityList(List<ServiceReceiptDTO> dtoList) {
        return null;
    }
}
