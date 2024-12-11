package com.skillfactory.finalproject.controller;

import com.skillfactory.finalproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/balance/{id}")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {

        return ResponseEntity.ok(accountService.getBalance(id));
    }

    @PutMapping("/taking/{id}/{summ}")
    public ResponseEntity<Integer> takeMoney(@PathVariable Long id, @PathVariable Double summ) {

        return ResponseEntity.ok(accountService.takeMoney(id, summ));
    }

    @PutMapping("/replenishment/{id}/{summ}")
    public ResponseEntity<Integer> putMoney(@PathVariable Long id, @PathVariable Double summ) {

        return ResponseEntity.ok(accountService.putMoney(id, summ));
    }

}
