package TestBHDStar.service.Impl;

import TestBHDStar.Repository.AccountRepository;
import TestBHDStar.service.AccountService;
import TestBHDStar.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountEntity> findByEmailAndPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email,password);
    }
}
