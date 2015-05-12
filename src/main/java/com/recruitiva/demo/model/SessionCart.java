package com.recruitiva.demo.model;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionCart implements Cart {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCart.class);

    CartInfo info = new CartInfo();

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing Cart");
    }

    @Override
    public CartInfo getInfo() {
        return info;
    }
}
