package com.enviro.assessment.grad001.charitychichichi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@RestResource(path = "addresses",rel = "addresses")
public class Address extends BaseEntity {

    @NotBlank(message = "Street name cannot be empty")
    private String street;

    @NotBlank(message = "city cannot be empty")
    private String city;

    @NotBlank(message = "country cannot be empty")
    private String country;

    @OneToOne
    @NotNull(message = "Address needs to belong to an Investor")
    @JsonBackReference
    private Investor investor;

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", investor=" + investor +
                '}';
    }
}
