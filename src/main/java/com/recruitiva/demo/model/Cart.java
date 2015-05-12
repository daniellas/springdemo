package com.recruitiva.demo.model;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Cart {

    CartInfo getInfo();
}
