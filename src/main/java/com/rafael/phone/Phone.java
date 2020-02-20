package com.rafael.phone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafael.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "number is required")
    private String number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
