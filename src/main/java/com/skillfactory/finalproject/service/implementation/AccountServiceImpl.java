package com.skillfactory.finalproject.service.implementation;

import com.skillfactory.finalproject.entity.Account;
import com.skillfactory.finalproject.entity.Operation;
import com.skillfactory.finalproject.exception.AccountNotFoundException;
import com.skillfactory.finalproject.exception.AccountSimilarException;
import com.skillfactory.finalproject.exception.DepositIsNegativeException;
import com.skillfactory.finalproject.exception.MoneyNotEnoughException;
import com.skillfactory.finalproject.repository.AccountRepository;
import com.skillfactory.finalproject.repository.OperationRepository;
import com.skillfactory.finalproject.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Override
    public double getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()
                -> new AccountNotFoundException("Account not found with id: " + accountId));
        return account.getBalance();
    }

    @Override
    @Transactional
    public int takeMoney(Long accountId, double amount) {
        if (amount < 0) {
            throw new DepositIsNegativeException("Cash withdrawal amount is negative");
        }
        Account account = getAccount(accountId);
        if (account.getBalance() <= amount) {
            throw new MoneyNotEnoughException("Money is not enough in deposit");
        } else {
            Operation operation = new Operation(amount, "Cнятие денег", account, LocalDateTime.now());
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            operationRepository.save(operation);
            return 1;
        }
    }

    @Override
    @Transactional
    public int putMoney(Long accountId, double amount) {
        if (amount < 0) {
            throw new DepositIsNegativeException("Cash withdrawal amount is negative");
        }
        Account account = getAccount(accountId);
        Operation operation = new Operation(amount, "Пополнение счета", account, LocalDateTime.now());
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        operationRepository.save(operation);
        return 1;
    }

    public List<Operation> getOperationList(Long accountId, LocalDateTime from, LocalDateTime to) {
        Account account = getAccount(accountId);
        List<Operation> result;
        if (from == null || to == null)
            result = operationRepository.findAllByClientId(accountId);
        else
            result = operationRepository.findAllFromTo(accountId, from, to);
        return result;
    }

    @Override
    @Transactional
    public int transferMoney(Long accountId, Long toAccountId, double amount) {
        if (accountId == toAccountId) {
            throw new AccountSimilarException("You can't transfer money to yourself");
        }
        if (amount < 0) {
            throw new DepositIsNegativeException("Cash withdrawal amount is negative");
        }
        Account accountFrom = getAccount(accountId);
        if (accountFrom.getBalance() <= amount) {
            throw new MoneyNotEnoughException("Money is not enough in deposit");
        }
        Account accountTo = getAccount(toAccountId);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        Operation operationFrom = new Operation(amount, "Перевод средств", accountFrom, LocalDateTime.now());
        accountTo.setBalance(accountTo.getBalance() + amount);
        Operation operationTo = new Operation(amount, "Зачисление средств", accountTo, LocalDateTime.now());
        accountRepository.save(accountFrom);
        operationRepository.save(operationFrom);
        accountRepository.save(accountTo);
        operationRepository.save(operationTo);
        return 1;
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow
                (() -> new AccountNotFoundException("Account not found with id: " + accountId));
    }
}
