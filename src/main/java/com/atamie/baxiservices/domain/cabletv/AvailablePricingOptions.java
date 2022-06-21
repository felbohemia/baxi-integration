package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailablePricingOptions {
    private PriceOption [] availablePricingOptions;
    private String code;
    private String name;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PriceOption[] getAvailablePricingOptions() {
        return availablePricingOptions;
    }

    public void setAvailablePricingOptions(PriceOption[] availablePricingOptions) {
        this.availablePricingOptions = availablePricingOptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AvailablePricingOptions{" +
                "availablePricingOptions=" + Arrays.toString(availablePricingOptions) +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
