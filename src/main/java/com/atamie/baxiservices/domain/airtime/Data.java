package com.atamie.baxiservices.domain.airtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private Provider[] providers;

    public Provider[] getProviders() {
        return providers;
    }
    public void setProviders(Provider[] providers) {
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Data{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
}
