package com.atamie.baxiservices.domain.electricity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ElectricityBuyRequestBody {
    private String account_number;
    private String amount;
    private String phone;
    private String service_type;
    private int agentId;
    private String agentReference;

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public void setAgentReference(String agentReference) {
        this.agentReference = agentReference;
    }
}
