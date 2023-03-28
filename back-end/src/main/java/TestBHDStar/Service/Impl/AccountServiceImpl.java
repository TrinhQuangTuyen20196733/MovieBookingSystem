package TestBHDStar.Service.Impl;

import TestBHDStar.Repository.AccountRepository;
import TestBHDStar.Service.AccountService;
import TestBHDStar.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<AccountEntity> findByEmailAndPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email,password);
    }
}
