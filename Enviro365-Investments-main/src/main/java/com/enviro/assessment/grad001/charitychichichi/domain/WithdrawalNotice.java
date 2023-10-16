package com.enviro.assessment.grad001.charitychichichi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Properties;

@Entity
@RestResource(path = "withdrawalNotices",rel = "withdrawalNotices") // as example this is going to create Rest Api end point for you
public class WithdrawalNotice extends BaseEntity {

    private BigDecimal amount = BigDecimal.ZERO; //initial is Zero

    private LocalDate date = LocalDate.now();
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JsonManagedReference
    private BankingDetails bankingDetails;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Product product;
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public BankingDetails getBankingDetails() {
        return bankingDetails;
    }
    public void setBankingDetails(BankingDetails bankingDetails) {
        this.bankingDetails = bankingDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "WithdrawalNotice{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }

    @PrePersist
    public void updateProductBalance() {
        BigDecimal currentBalance = product.getCurrentBalance();
        BigDecimal withdrawalAmount = amount;
        BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);

        // Validate the withdrawal amount
        if (withdrawalAmount.compareTo(currentBalance) > 0 || withdrawalAmount.compareTo(newBalance.multiply(new BigDecimal("0.9"))) > 0
        ) {
            throw new ConstraintViolationException("Withdrawal cant happen is not valid because the withdrawal amount cannot be greater than product current balance or 90% of the current balance", null);
        }

        product.setCurrentBalance(newBalance);
    }

    @PostPersist
    public void sendWithdrawalEmail() {
        Investor investor = product.getInvestor();
        String subject = "Withdrawal Notice Created";

        BigDecimal oldBalance = product.getCurrentBalance().add(amount) ;
        String body = "Dear " + investor.getName() + ",\n\nYour withdrawal notice of the balance was  " + oldBalance+ " and the amount withdrawn is " + amount +" And the current balance is " + product.getCurrentBalance();

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "your.smtp.host");
        properties.setProperty("mail.smtp.port", "your.smtp.port");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new
                        PasswordAuthentication("ruvarasshechichichi@gmail.com", "Raphael8375@cRc");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ruvarasshechichichi@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(investor.getEmail()));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
