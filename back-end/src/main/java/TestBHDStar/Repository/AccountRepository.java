package TestBHDStar.Repository;

import TestBHDStar.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

    Optional<AccountEntity> findByEmailAndPassword(String email, String password);

    AccountEntity findByEmail(String email);
}
