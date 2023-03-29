package TestBHDStar.service.Impl;

import TestBHDStar.Repository.RoleRepository;
import TestBHDStar.service.RoleService;
import TestBHDStar.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleEntity> findRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<RoleEntity> findRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
