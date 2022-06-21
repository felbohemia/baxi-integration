package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private String statusCode;
    private String transactionStatus;
    private int transactionReference;
    private String transactionMessage;
    private int baxiReference;
    private String provider_message;
    private RawOutput rawOutput;

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setTransactionReference(int transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setBaxiReference(int baxiReference) {
        this.baxiReference = baxiReference;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getBaxiReference() {
        return baxiReference;
    }

    public int getTransactionReference() {
        return transactionReference;
    }

    public RawOutput getRawOutput() {
        return rawOutput;
    }

    public String getProvider_message() {
        return provider_message;
    }

    public void setProvider_message(String provider_message) {
        this.provider_message = provider_message;
    }

    public void setRawOutput(RawOutput rawOutput) {
        this.rawOutput = rawOutput;
    }

    @Override
    public String toString() {
        return "Data{" +
                "statusCode='" + statusCode + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", transactionReference=" + transactionReference +
                ", transactionMessage='" + transactionMessage + '\'' +
                ", baxiReference=" + baxiReference +
                ", provided_message='" + provider_message + '\'' +
                ", rawOutput=" + rawOutput +
                '}';
    }
}
