package com.atamie.baxiservices.domain.databundle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPartBuyBundleResponse {
    private String statusCode;
    private String transactionStatus;
    private String transactionReference;
    private String transactionMessage;
    private String baxiReference;
    private String provider_message;
    private BuyBundleExtraDataResponse extraData;

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public BuyBundleExtraDataResponse getExtraData() {
        return extraData;
    }

    public String getBaxiReference() {
        return baxiReference;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public void setBaxiReference(String baxiReference) {
        this.baxiReference = baxiReference;
    }

    public String getProvider_message() {
        return provider_message;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setProvider_message(String provider_message) {
        this.provider_message = provider_message;
    }

    public void setExtraData(BuyBundleExtraDataResponse extraData) {
        this.extraData = extraData;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "DataPartBuyBundleResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", transactionReference='" + transactionReference + '\'' +
                ", transactionMessage='" + transactionMessage + '\'' +
                ", baxiReference='" + baxiReference + '\'' +
                ", provider_message='" + provider_message + '\'' +
                ", extraData=" + extraData +
                '}';
    }
}
