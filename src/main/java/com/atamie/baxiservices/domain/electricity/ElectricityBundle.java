package com.atamie.baxiservices.domain.electricity;

public class ElectricityBundle {
    private String service_type;
    private String account_number;

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_type() {
        return service_type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Override
    public String toString() {
        return "ElectricityBundle{" +
                "service_type='" + service_type + '\'' +
                ", account_number='" + account_number + '\'' +
                '}';
    }
}
