package com.skillfactory.finalproject.service;

import com.skillfactory.finalproject.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {

    public double getBalance(Long accountId);

    public int takeMoney(Long accountId, double amount);

    public int putMoney(Long accountId, double amount);

    public List<Operation> getOperationList(Long accountId, LocalDateTime from, LocalDateTime to);

    public int transferMoney(Long accountId, Long toAccountId, double amount);

}
