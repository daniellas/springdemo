package com.recruitiva.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderData {

    @NotNull
    @Size(min = 1, max = 255)
    String clientEmail;

    @NotNull
    @Size(min = 1, max = 255)
    String address;

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
