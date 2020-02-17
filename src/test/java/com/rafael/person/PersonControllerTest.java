package com.rafael.person;

import com.rafael.exception.PersonNotFoundException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static com.rafael.utils.GenerateIdUtilsTest.generateUniqueId;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String URI = "api/v1/people";
    private static final String URI_UPDATE = "api/v1/people/updatePerson";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldReturnHttpStatus200WhenAValidIdIsReported() {

        ResponseEntity<Person> person = testRestTemplate.getForEntity(HTTP_LOCALHOST + port + URI + "/10", Person.class, String.class);

        assertEquals(HttpStatus.OK.value(), person.getStatusCode().value());
        assertTrue(person.toString().contains("Rafa"));
        assertTrue(person.toString().contains("22"));
        assertTrue(person.toString().contains("M"));
    }

    @Test
    void shouldReturnHttpStatus404WhenTheGivenIdIsInvalid() {

        ResponseEntity<Map> person = testRestTemplate.getForEntity(HTTP_LOCALHOST + port + URI + "/0", Map.class, String.class);

        assertEquals(HttpStatus.NOT_FOUND.value(), person.getStatusCode().value());
        assertEquals("This person doesn't exist", person.getBody().get("message"));
        assertEquals("404 NOT_FOUND", person.getBody().get("error"));
    }

    @Test
    void deveriaRetornarHttpStatus201QuandoForInseridoUmaPessoaComSucesso() {

        Person newPerson = getPerson(generateUniqueId(), "Mariazinha1", "44", "F");

        ResponseEntity<Map> person = testRestTemplate.postForEntity(HTTP_LOCALHOST + port + URI, newPerson, Map.class);

        assertEquals(HttpStatus.CREATED.value(), person.getStatusCode().value());
        assertNotNull(person.getBody().get("id"));
        assertEquals(newPerson.getName(), person.getBody().get("name"));
        assertEquals(newPerson.getAge(), person.getBody().get("age"));
        assertEquals(newPerson.getGender(), person.getBody().get("gender"));
    }

    @Test
    void shouldReturnHttpStatus500WhenAnExistingPersonIsAdded() {

        Person newPerson = getPerson(7, "Mariazinha", "null", "null");

        ResponseEntity<Map> person = testRestTemplate.postForEntity(HTTP_LOCALHOST + port + URI, newPerson, Map.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), person.getStatusCode().value());
        assertEquals("This person already exists!" , person.getBody().get("message"));
        assertEquals("500 INTERNAL_SERVER_ERROR", person.getBody().get("error"));
    }

    @Test
    void shouldReturnHttpStatus200WhenAPersonIsSuccessfullyEdited() {

        Person newPerson = getPerson(7, "Mariazinha", "44", "F");

        ResponseEntity<Map> person = testRestTemplate.postForEntity(HTTP_LOCALHOST + port + URI_UPDATE, newPerson, Map.class);

        assertEquals(HttpStatus.OK.value(), person.getStatusCode().value());
        assertNotNull(person.getBody().get("id"));
        assertEquals(newPerson.getName(), person.getBody().get("name"));
        assertEquals(newPerson.getAge(), person.getBody().get("age"));
        assertEquals(newPerson.getGender(), person.getBody().get("gender"));

    }

    @Test
    void shouldReturnHttpStatus404WhenInvalidIdIsReportedInEdition() {

        Person newPerson = getPerson(0, "Mariazinha", "44", "F");

        ResponseEntity<Map> person = testRestTemplate.postForEntity(HTTP_LOCALHOST + port + URI_UPDATE, newPerson, Map.class);

        assertEquals(HttpStatus.NOT_FOUND.value(), person.getStatusCode().value());
        assertEquals("This person doesn't exist" , person.getBody().get("message"));
        assertEquals("404 NOT_FOUND", person.getBody().get("error"));
    }

    private Person getPerson(Integer integer, String name, String age, String gender) {
        return new Person(integer, name, age, gender);
    }

}
