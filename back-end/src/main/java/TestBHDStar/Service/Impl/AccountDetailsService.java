package TestBHDStar.Service.Impl;

import TestBHDStar.security.AccountDetails;
import TestBHDStar.Repository.AccountRepository;
import TestBHDStar.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity  account = accountRepository.findByEmail(email);
        if (account==null) {
            throw new UsernameNotFoundException(email);
        }
        return  new AccountDetails(account);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Account not found with id : " + id)
        );

        return new AccountDetails(account);
    }
}
