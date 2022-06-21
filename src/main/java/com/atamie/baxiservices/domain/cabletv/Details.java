package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {
    private String customerCareReferenceId;
    private String exchangeReference;
    private String errorMessage;
    private String errorCode;
    private String errorId;
    private String auditReferenceNumber;
    private boolean done;
    private String status;

    public String getExchangeReference() {
        return exchangeReference;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setExchangeReference(String exchangeReference) {
        this.exchangeReference = exchangeReference;
    }

    public boolean isDone() {
        return done;
    }

    public String getAuditReference() {
        return auditReferenceNumber;
    }

    public String getCustomerCareReference() {
        return customerCareReferenceId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setAuditReference(String auditReference) {
        this.auditReferenceNumber = auditReference;
    }

    public void setCustomerCareReference(String customerCareReference) {
        this.customerCareReferenceId = customerCareReference;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Details{" +
                "customerCareReference='" + customerCareReferenceId + '\'' +
                ", exchangeReference='" + exchangeReference + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorId='" + errorId + '\'' +
                ", auditReference='" + auditReferenceNumber + '\'' +
                ", done=" + done +
                ", status='" + status + '\'' +
                '}';
    }
}
