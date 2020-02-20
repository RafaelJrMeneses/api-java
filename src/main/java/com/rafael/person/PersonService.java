package com.rafael.person;

import com.rafael.exception.PersonAlreadyExistsException;
import com.rafael.exception.PersonNotFoundException;
import com.rafael.phone.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;


    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person getPerson(Integer id) {

        Optional<Person> idPerson = repository.findById(id);

        if (!idPerson.isPresent()) {
            throw new PersonNotFoundException();
        }

        return idPerson.get();
    }

    public Person save(Person person) {

        Optional<Person> idPerson = repository.findById(person.getId());

        if (idPerson.isPresent()) {
            throw new PersonAlreadyExistsException();
        }

        List<Phone> phone = person.getPhones();
        for (Phone phones: phone){
            phones.setPerson(person);
        }
        var newPerson = repository.save(person);

        return newPerson;

    }

    public Person update(Person person) {

        if (!repository.existsById(person.getId())){
            throw new PersonNotFoundException();
        }

        List<Phone> phone = person.getPhones();
        for (Phone phones: phone){
            phones.setPerson(person);
        }

        var newPerson = repository.save(person);

        return newPerson;
    }

    public void delete(Integer id) {

        if(!repository.existsById(id)) {
            throw new PersonNotFoundException();
        }
        repository.deleteById(id);
    }
}
