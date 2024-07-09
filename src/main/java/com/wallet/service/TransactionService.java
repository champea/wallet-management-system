package com.wallet.service;

import com.wallet.model.Transaction;
import com.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Long accountId, BigDecimal amount) {
        accountService.updateBalance(accountId, amount);
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> listTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public void transferBetweenAccounts(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        createTransaction(fromAccountId, amount.negate());
        createTransaction(toAccountId, amount);
    }
}
