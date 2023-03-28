package TestBHDStar.Service;

import TestBHDStar.entity.AccountEntity;

import java.util.Optional;

public interface AccountService {
    Optional<AccountEntity> findByEmailAndPassword(String email, String password);
}
