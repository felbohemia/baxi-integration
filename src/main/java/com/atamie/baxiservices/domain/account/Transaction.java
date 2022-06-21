package com.atamie.baxiservices.domain.account;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String account;
    @NotNull
    private String phone;
    @NotNull
    private String type;//transaction type{debit|credit}
    @NotNull
    private double amount;
    @NotNull
    private LocalDateTime period;

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPeriod(LocalDateTime period) {
        this.period = period;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getPeriod() {
        return period;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", period=" + period +
                '}';
    }
}
