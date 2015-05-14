package com.recruitiva.demo.model;

import java.util.List;

import com.recruitiva.demo.entity.ShopOrder;

public interface Seller {
    CartContent acceptOrder(OrderData data);

    List<ShopOrder> orders();
}
