package com.recruitiva.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruitiva.demo.entity.ShopOrder;
import com.recruitiva.demo.model.Cart;
import com.recruitiva.demo.model.CartContent;
import com.recruitiva.demo.model.OrderData;
import com.recruitiva.demo.model.Seller;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    Seller seller;

    @Autowired
    Cart cart;

    @RequestMapping(method = RequestMethod.GET)
    List<ShopOrder> orders() {
        return seller.orders();
    }

    @RequestMapping(method = RequestMethod.POST)
    CartContent accept(@RequestBody OrderData data) {
        return seller.acceptOrder(data);
    }
}
