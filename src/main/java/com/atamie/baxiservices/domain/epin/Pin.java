package com.atamie.baxiservices.domain.epin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pin {
    private String instructions;
    private String serialNumber;
    private  String pin;
    private String expiresOn;

    public String getExpiresOn() {
        return expiresOn;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPin() {
        return pin;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setExpiresOn(String expiresOn) {
        this.expiresOn = expiresOn;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "instructions='" + instructions + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", expiresOn='" + expiresOn + '\'' +
                '}';
    }
}
