package com.rafael.person;

import com.rafael.exception.PersonAlreadyExistsException;
import com.rafael.exception.PersonNotFoundException;
import com.rafael.phone.Phone;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.rafael.utils.GenerateIdUtilsTest.generateUniqueId;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonRepository repository;

    @Mock
    private Person person;
    private List<Phone> phones;

    @Test
    void shouldReturnAPersonWhenAValidIdIsInformed() {

        Mockito.when(person.getId()).thenReturn(10);

        var returnPerson = service.getPerson(person.getId());

        assertEquals(10, returnPerson.getId());
        assertEquals("Rafa", returnPerson.getName());
        assertEquals("22", returnPerson.getAge());
        assertEquals("M", returnPerson.getGender());
    }

    @Test
    void shouldReturnPersonNotFoundExceptionWhenAnInvalidIdIsReported() {

        assertThrows(PersonNotFoundException.class, () -> service.getPerson(0));
    }

    @Test
    void shouldCreateANewPerson() {

        person = createPerson();

        Mockito.when(person.getId()).thenReturn(generateUniqueId());
        var newPerson = service.save(person);

        assertNotNull(newPerson.getId());
        assertEquals("Rafa", newPerson.getName());
        assertEquals("22", newPerson.getAge());
        assertEquals("M", newPerson.getGender());
    }

    @Test
    void shouldReturnPersonAlreadyExistsExceptionWhenAnPersonAlreadsExists() {

        person = createPerson();
        person.setId(10);//Solução paliativa

        assertThrows(PersonAlreadyExistsException.class, () -> service.save(person));
    }

    @Test
    void shouldReturnANewPersonWhenEditing() {

        person = createPerson();
        person.setId(5);
        person.setName("Gabriel");

        var newPerson = service.update(person);

        assertNotNull(newPerson.getId());
        assertEquals("Gabriel", newPerson.getName());
        assertEquals("22", newPerson.getAge());
        assertEquals("M", newPerson.getGender());
    }

    @Test
    void shouldReturnPersonNotFoundExceptionWhenTheGivenIdDoesNotExist() {

        person = createPerson();

        Mockito.when(person.getId()).thenReturn(3);

        assertThrows(PersonNotFoundException.class, () -> service.update(person));
    }

    @Test
    void shouldReturnPersonNotFoundExceptionWhenGivenAnIdThatDoesNotExist() {

        Mockito.when(person.getId()).thenReturn(0);

        assertThrows(PersonNotFoundException.class, () -> service.delete(person.getId()));

    }

    private Person createPerson() {
        return new Person(person.getId(),"Rafa", "22", "M", createPhone());
    }

    private List<Phone> createPhone() {
        var newphones = new Phone(1, "14252", new Person());

        List<Phone> phones = new ArrayList<>();
        phones.add(newphones);

        return phones;
    }
}
