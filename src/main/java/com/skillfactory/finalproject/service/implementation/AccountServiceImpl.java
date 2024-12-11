package com.skillfactory.finalproject.service.implementation;

import com.skillfactory.finalproject.entity.Account;
import com.skillfactory.finalproject.exception.AccountNotFoundException;
import com.skillfactory.finalproject.exception.DepositIsNegativeException;
import com.skillfactory.finalproject.exception.MoneyNotEnoughException;
import com.skillfactory.finalproject.repository.AccountRepository;
import com.skillfactory.finalproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public double getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()
                -> new AccountNotFoundException("Account not found with id: " + accountId));
        return account.getBalance();
    }

    @Override
    public int takeMoney(Long accountId, double amount) {
        if (amount < 0) {
            throw new DepositIsNegativeException("Cash withdrawal amount is negative");
        }
        Account account = accountRepository.findById(accountId).orElseThrow
                (() -> new AccountNotFoundException("Account not found with id: " + accountId));
        if (account.getBalance() <= amount) {
            throw new MoneyNotEnoughException("Money is not enough in deposit");
        } else {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            return 1;
        }
    }

    @Override
    public int putMoney(Long accountId, double amount) {
        if (amount < 0) {
            throw new DepositIsNegativeException("Cash withdrawal amount is negative");
        }
        Account account = accountRepository.findById(accountId).orElseThrow
                (() -> new AccountNotFoundException("Account not found with id: " + accountId));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return 1;
    }
}
