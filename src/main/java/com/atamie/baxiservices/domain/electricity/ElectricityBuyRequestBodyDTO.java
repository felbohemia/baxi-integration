package com.atamie.baxiservices.domain.electricity;

public class ElectricityBuyRequestBodyDTO {

    private String account_number;
    private String amount;
    private String phone;
    private String service_type;

    public String getAccount_number() {
        return account_number;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getService_type() {
        return service_type;
    }

    public String getAmount() {
        return amount;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "ElectricityBuyRequestBodyDTO{" +
                "account_number='" + account_number + '\'' +
                ", amount='" + amount + '\'' +
                ", phone='" + phone + '\'' +
                ", service_type='" + service_type + '\'' +
                '}';
    }
}
