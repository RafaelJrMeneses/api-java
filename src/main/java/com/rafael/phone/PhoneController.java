package com.rafael.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/phones")
@RestController
public class PhoneController {

    @Autowired
    private PhoneRepository repository;

    @GetMapping
    public List<Phone> getAll() {
        return repository.findAll();
    }
}
