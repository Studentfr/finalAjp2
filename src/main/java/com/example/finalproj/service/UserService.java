package com.example.finalproj.service;

import com.example.finalproj.config.SpringSecurityConfig;
import com.example.finalproj.repository.AccountRepository;
import com.example.finalproj.repository.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(AccountRepository accountRepository, PasswordEncoder securityConfig) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = securityConfig;
    }

    public void registerUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public Account getUser(Long id) {
        return accountRepository.getOne(id);
    }
}
