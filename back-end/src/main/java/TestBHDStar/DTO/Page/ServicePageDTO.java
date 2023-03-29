package TestBHDStar.DTO.Page;

import TestBHDStar.DTO.ServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicePageDTO  extends  BasePage{
    List<ServiceDTO> serviceDTOList;
}
