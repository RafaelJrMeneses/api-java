package com.rafael.person;

import com.rafael.exception.PersonException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {

    @Autowired
    private PersonService service;


    @GetMapping("/all")
    @ApiOperation("List all people")
    public ResponseEntity<List<Person>> get() {

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find person by ID")
    public ResponseEntity<Person> getPerson(@Valid @PathVariable Integer id) {
       return new ResponseEntity<>(service.getPerson(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create person")
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, @ApiIgnore Errors err) {

        if(err.hasErrors()) {
            throw new PersonException(err.getFieldError());
        }
        var save = service.save(person);

        return new ResponseEntity<Person>(save, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @ApiOperation("Update person")
    public ResponseEntity<Person> update(@Valid @RequestBody Person person) {

        var newPerson = service.update(person);

        return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Integer id) {

        service.delete(id);

        return new ResponseEntity<String>("Person successfully deleted" , HttpStatus.OK);
    }
}
