package com.rafael.phone;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/phones")
@RestController
public class PhoneController {

    @Autowired
    private PhoneRepository repository;

    @GetMapping("/all")
    @ApiOperation("List all phone")
    public List<Phone> getAll() {
        return repository.findAll();
    }
}
