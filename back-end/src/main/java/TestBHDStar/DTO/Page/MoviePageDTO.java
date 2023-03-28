package TestBHDStar.DTO.Page;

import TestBHDStar.DTO.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePageDTO  extends BasePage{
    private List<MovieDTO> movieDTOList;
}
