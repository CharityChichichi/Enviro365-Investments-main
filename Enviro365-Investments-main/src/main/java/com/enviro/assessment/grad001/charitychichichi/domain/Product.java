package com.enviro.assessment.grad001.charitychichichi.domain;


import com.enviro.assessment.grad001.charitychichichi.validators.RetirementProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product extends BaseEntity {

    @NotNull
//    @RetirementProduct
    private Type type;

    @NotBlank(message = "name cannot be empty")
    private String name;

    private BigDecimal currentBalance = BigDecimal.ZERO; //initial is Zero
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WithdrawalNotice> withdrawalNotices;

    @ManyToOne
    @JsonBackReference
    private Investor investor;

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
    public List<WithdrawalNotice> getWithdrawalNotices() {
        return withdrawalNotices;
    }
    public void setWithdrawalNotices(List<WithdrawalNotice> withdrawalNotices) {
        this.withdrawalNotices = withdrawalNotices;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    @PrePersist
    public void validate() {
        if (type == Type.RETIREMENT) {
            Investor investor = getInvestor();
            if (investor == null || investor.getAge() < 65) {
                assert investor != null;
                throw new IllegalStateException("If product type is RETIREMENT then investor age must be greater than or equal to 65 but this investor is " + investor.getAge());
            }
        }
    }



    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", currentBalance=" + currentBalance +
                ", withdrawalNotices=" + withdrawalNotices +
                ", investor=" + investor +
                '}';
    }
}
