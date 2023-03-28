package TestBHDStar.Service;

import TestBHDStar.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findById(int id);
    Optional<RoleEntity> findByCode( String code);
}
