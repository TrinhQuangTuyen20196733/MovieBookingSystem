package TestBHDStar.DTO;

import TestBHDStar.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSystemDTO {
    int id;
    private String name;
    private String address;
}
