package com.atamie.baxiservices.domain.epin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PinBuyData {
    private String statusCode;
    private String transactionStatus;
    private double transactionReference;
    private String transactionMessage;
    private Pin [] pins;
    private double baxiReference;
    private String provider_message;

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public void setBaxiReference(double baxiReference) {
        this.baxiReference = baxiReference;
    }

    public void setTransactionReference(double transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getProvider_message() {
        return provider_message;
    }

    public double getTransactionReference() {
        return transactionReference;
    }

    public void setProvider_message(String provider_message) {
        this.provider_message = provider_message;
    }

    public Pin[] getPins() {
        return pins;
    }

    public double getBaxiReference() {
        return baxiReference;
    }

    public void setPins(Pin[] pins) {
        this.pins = pins;
    }

    @Override
    public String toString() {
        return "PinBuyData{" +
                "statusCode='" + statusCode + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", transactionReference=" + transactionReference +
                ", transactionMessage='" + transactionMessage + '\'' +
                ", pins=" + Arrays.toString(pins) +
                ", baxiReference=" + baxiReference +
                ", provider_message='" + provider_message + '\'' +
                '}';
    }
}
