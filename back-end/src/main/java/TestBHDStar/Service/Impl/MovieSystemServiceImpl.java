package TestBHDStar.Service.Impl;

import TestBHDStar.DTO.MovieSystemDTO;
import TestBHDStar.Repository.MovieSystemRepository;
import TestBHDStar.Service.MovieSystemService;
import TestBHDStar.mapper.mapperImpl.MovieSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieSystemServiceImpl implements MovieSystemService {
    @Autowired
    MovieSystemRepository movieSystemRepository;
    @Override
    public List<MovieSystemDTO> findAll() {
        return MovieSystemMapper.getInstance().toDTOList(movieSystemRepository.findAll());
    }
}
