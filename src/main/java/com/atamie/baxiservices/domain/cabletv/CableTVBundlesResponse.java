package com.atamie.baxiservices.domain.cabletv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CableTVBundlesResponse {
    private String status;
    private String message;
    private int code;
    private AvailablePricingOptions[] data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(AvailablePricingOptions[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public AvailablePricingOptions[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CableTVBundlesResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
