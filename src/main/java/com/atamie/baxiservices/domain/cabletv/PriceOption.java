package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceOption {
    private String monthsPaidFor;
    private int price;
    private int invoicePeriod;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getInvoicePeriod() {
        return invoicePeriod;
    }

    public String getMonthsPaidFor() {
        return monthsPaidFor;
    }

    public void setInvoicePeriod(int invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
    }

    public void setMonthsPaidFor(String monthsPaidFor) {
        this.monthsPaidFor = monthsPaidFor;
    }

    @Override
    public String toString() {
        return "PriceOption{" +
                "monthsPaidFor='" + monthsPaidFor + '\'' +
                ", price=" + price +
                ", invoicePeriod=" + invoicePeriod +
                '}';
    }
}
