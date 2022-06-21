package com.atamie.baxiservices.domain.databundle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BundleDetails {
    private String name;
    private String allowance;
    private int price;
    private String validity;
    private int datacode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDatacode() {
        return datacode;
    }

    public int getPrice() {
        return price;
    }

    public String getAllowance() {
        return allowance;
    }

    public String getValidity() {
        return validity;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public void setDatacode(int datacode) {
        this.datacode = datacode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "BundleDetails{" +
                "name='" + name + '\'' +
                ", allowance='" + allowance + '\'' +
                ", price=" + price +
                ", validity='" + validity + '\'' +
                ", datacode=" + datacode +
                '}';
    }
}
