package com.rafael.bank_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountRepository repository;

    @GetMapping("/all")
    public List<BankAccount> getAll() {
        return repository.findAll();
    }
}
