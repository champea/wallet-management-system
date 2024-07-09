package com.wallet.service;

import com.wallet.model.Account;
import com.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String owner) {
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(BigDecimal.valueOf(0));
        return accountRepository.save(account);
    }

    public BigDecimal getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"))
                .getBalance();
    }

    public void updateBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }
}
