package com.atamie.baxiservices.domain.databundle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyBundleExtraDataResponse {
    private String balance;
    private String exchangeReference;
    private String responseMessage;
    private String status;
    private String statusCode;
    private String responseCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalance() {
        return balance;
    }

    public String getExchangeReference() {
        return exchangeReference;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setExchangeReference(String exchangeReference) {
        this.exchangeReference = exchangeReference;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "BuyBundleExtraDataResponse{" +
                "balance='" + balance + '\'' +
                ", exchangeReference='" + exchangeReference + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", responseCode='" + responseCode + '\'' +
                '}';
    }
}
