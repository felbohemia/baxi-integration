package com.atamie.baxiservices.domain.account;

public class CreditAccountDTO {
    private String account;
    private String depositorPhone;
    private double amount;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDepositorPhone() {
        return depositorPhone;
    }

    public void setDepositorPhone(String depositorPhone) {
        this.depositorPhone = depositorPhone;
    }

    @Override
    public String toString() {
        return "CreditAccountDTO{" +
                "account='" + account + '\'' +
                ", depositorPhone='" + depositorPhone + '\'' +
                ", amount=" + amount +
                '}';
    }
}
