package com.atamie.baxiservices.domain.databundle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyBundleResponse {
    private  String status;
    private String message;
    private String code;
    private DataPartBuyBundleResponse data;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setData(DataPartBuyBundleResponse data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataPartBuyBundleResponse getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BuyBundleResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
