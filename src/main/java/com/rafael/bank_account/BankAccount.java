package com.rafael.bank_account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafael.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Bank Name is required")
    private String bankName;

    @NotBlank(message = "Account is required")
    private String account;

    @NotBlank(message = "Agency is required")
    private String agency;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private Person person;
}
