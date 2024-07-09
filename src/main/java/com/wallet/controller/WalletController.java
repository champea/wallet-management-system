package com.wallet.controller;

import com.wallet.model.Account;
import com.wallet.model.Transaction;
import com.wallet.service.AccountService;
import com.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(
            @RequestParam String owner) {
        Account account = accountService.createAccount(owner);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable Long id) {
        try {
            BigDecimal balance = accountService.getBalance(id);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount) {
        try {
            Transaction transaction = transactionService.createTransaction(accountId, amount);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/{id}/transactions")
    public ResponseEntity<List<Transaction>> listTransactions(
            @PathVariable Long id) {
        try {
            List<Transaction> transactions = transactionService.listTransactions(id);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount) {
        try {
            transactionService.transferBetweenAccounts(fromAccountId, toAccountId, amount);
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}