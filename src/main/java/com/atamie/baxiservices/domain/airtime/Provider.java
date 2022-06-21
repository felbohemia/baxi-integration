package com.atamie.baxiservices.domain.airtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Provider {
    private String service_type;
    private String shortname;


    private int biller_id;
    private int product_id;

    private String name;
    private String [] plan;

    public void setBiller_id(int biller_id) {
        this.biller_id = biller_id;
    }

    public void setProduct_id(int product) {
        this.product_id = product;
    }
    //@JsonProperty("product_id")
    public int getProduct_id() {
        return product_id;
    }

    public String getService_type() {
        return service_type;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public String[] getPlan() {
        return plan;
    }

    public int getBiller_id() {
        return biller_id;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlan(String[] plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "service_type='" + service_type + '\'' +
                ", shortname='" + shortname + '\'' +
                ", biller_id=" + biller_id +
                ", product_id=" + product_id +
                ", name='" + name + '\'' +
                ", plan=" + Arrays.toString(plan) +
                '}';
    }
}
