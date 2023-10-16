package com.enviro.assessment.grad001.charitychichichi.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Investor extends BaseEntity{

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotNull
    private LocalDate dob;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "Number cannot be empty")
    private String number;

    @NotNull(message = "Age cannot be empty")
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Investor{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", age=" + age +
                ", products=" + products +
                ", address=" + (address != null ? address.getId() : null) +
                '}';}
    }

