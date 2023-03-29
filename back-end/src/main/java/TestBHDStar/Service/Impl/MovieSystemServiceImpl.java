package TestBHDStar.service.Impl;

import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.Repository.MovieSystemRepository;
import TestBHDStar.service.MovieSystemService;
import TestBHDStar.mapper.mapperImpl.MovieSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieSystemServiceImpl implements MovieSystemService {
    private final MovieSystemRepository movieSystemRepository;

    public MovieSystemServiceImpl(MovieSystemRepository movieSystemRepository) {
        this.movieSystemRepository = movieSystemRepository;
    }

    @Override
    public List<MovieSystemDTO> findAll() {
        return MovieSystemMapper.getInstance().toDTOList(movieSystemRepository.findAll());
    }
}
