package com.skillfactory.finalproject.service;

public interface AccountService {

    public double getBalance(Long accountId);

    public int takeMoney(Long accountId, double amount);

    public int putMoney(Long accountId, double amount);
}
