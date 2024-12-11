package com.skillfactory.finalproject.repository;


import com.skillfactory.finalproject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
