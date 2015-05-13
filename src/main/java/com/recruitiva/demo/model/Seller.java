package com.recruitiva.demo.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.recruitiva.demo.entity.ShopOrder;

@Transactional
public interface Seller {
    CartContent acceptOrder(OrderData data);

    List<ShopOrder> orders();
}
