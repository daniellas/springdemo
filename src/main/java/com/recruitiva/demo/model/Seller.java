package com.recruitiva.demo.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.recruitiva.demo.entity.ShopOrder;

@Transactional
@Validated
public interface Seller {
    CartContent acceptOrder(@Valid OrderData data);

    List<ShopOrder> orders();
}
