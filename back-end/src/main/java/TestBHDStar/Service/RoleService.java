package TestBHDStar.service;

import TestBHDStar.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findRoleById(int id);
    Optional<RoleEntity> findRoleByCode( String code);
}
