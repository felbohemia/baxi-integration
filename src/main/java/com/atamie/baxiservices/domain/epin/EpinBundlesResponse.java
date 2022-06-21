package com.atamie.baxiservices.domain.epin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpinBundlesResponse {
    private String status;
    private String message;
    private int code;
    private DataItem[] data;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public DataItem[] getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(DataItem[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EpinBundlesResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
