package com.atamie.baxiservices.domain.epin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItem {
    private int amount;
    private int available;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "amount=" + amount +
                ", available=" + available +
                ", description='" + description + '\'' +
                '}';
    }
}
