package TestBHDStar.service;

import TestBHDStar.DTO.MovieSystemDTO;

import java.util.List;

public interface MovieSystemService {
    List<MovieSystemDTO> findAll();
}