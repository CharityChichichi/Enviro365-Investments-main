package com.enviro.assessment.grad001.charitychichichi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@RestResource(path = "bankingDetails",rel = "bankingDetails")
public class BankingDetails extends BaseEntity{

    @NotBlank(message = "Account number cannot be empty")
    @Size(min = 4 ,message = "Cant have less than 4 characters")
    private String accountNumber;

    @NotBlank(message = "Account type cannot be empty")
    private String accountType;

    @NotBlank(message = "bank name cannot be empty")
    private String bank;

    @NotBlank(message = "Branch Code cannot be empty")
    private String branchCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "withdrawal_notice_id") //ensure  each BankingDetails instance can belong to only one WithdrawalNotice. remove this if it joined account between investors
    @NotNull(message = "Banking Details needs have a withdrawal Notice")
    @JsonBackReference
    private WithdrawalNotice withdrawalNotice;

    public WithdrawalNotice getWithdrawalNotice() {
        return withdrawalNotice;
    }

    public void setWithdrawalNotice(WithdrawalNotice withdrawalNotice) {
        this.withdrawalNotice = withdrawalNotice;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        return "BankingDetails{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", bank='" + bank + '\'' +
                ", branchCode='" + branchCode + '\'' +
                '}';
    }
}
