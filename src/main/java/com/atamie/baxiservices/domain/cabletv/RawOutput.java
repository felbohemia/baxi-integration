package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawOutput {
    private int transactionNumber;
    private Details details;

    public Details getDetails() {
        return details;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public String toString() {
        return "RawOutput{" +
                "transactionNumber=" + transactionNumber +
                ", details=" + details +
                '}';
    }
}
