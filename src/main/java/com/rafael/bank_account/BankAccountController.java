package com.rafael.bank_account;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountRepository repository;

    @GetMapping("/all")
    @ApiOperation("List all Bank Accounts")
    public List<BankAccount> getAll() {
        return repository.findAll();
    }
}
