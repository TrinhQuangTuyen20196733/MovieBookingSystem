package TestBHDStar.DTO;

import TestBHDStar.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO  {
    int id;
    private String title;
    private byte[] thumbnail;
    private String alt;
    private String trailer;
    private String shortDescription;
    private String classify;
    private String director;
    private String actor;
    private String type;
    private Date startDate;
    private int length;
    private String language;
}
