package TestBHDStar.Service.Impl;

import TestBHDStar.Repository.RoleRepository;
import TestBHDStar.Service.RoleService;
import TestBHDStar.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Optional<RoleEntity> findById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<RoleEntity> findByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
