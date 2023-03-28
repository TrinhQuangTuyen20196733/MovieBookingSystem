package TestBHDStar.DTO;

import TestBHDStar.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO  {
    int id;
    private MovieSystemDTO movieSystemDTO;
    private String name;
    private String type;
}
