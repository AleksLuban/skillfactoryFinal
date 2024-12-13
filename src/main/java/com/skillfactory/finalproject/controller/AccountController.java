package com.skillfactory.finalproject.controller;

import com.skillfactory.finalproject.entity.Operation;
import com.skillfactory.finalproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/operations")
    public List<Operation> getOperationList(@RequestParam(name = "id") Long accountId,
                                            @RequestParam(name = "from", required = false) LocalDateTime from,
                                            @RequestParam(name = "to", required = false) LocalDateTime to) {

        return accountService.getOperationList(accountId, from, to);
    }

    @PutMapping("/transfer/{from}/{to}/{sum}")
    public ResponseEntity<Integer> transferMoney(@PathVariable Long from, @PathVariable Long to, @PathVariable Double sum) {

        return ResponseEntity.ok(accountService.transferMoney(from, to, sum));
    }

}
