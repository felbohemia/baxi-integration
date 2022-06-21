package com.atamie.baxiservices.domain.databundle;

import com.atamie.baxiservices.domain.airtime.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BundleResponses {
    private String status;
    private String message;
    private int code;
    private BundleDetails[] data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(BundleDetails[] data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public BundleDetails[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BundleResponses{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
