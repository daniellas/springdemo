package com.recruitiva.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruitiva.demo.model.Cart;
import com.recruitiva.demo.model.CartInfo;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    
    @Autowired
    Cart cart;
    
    @RequestMapping(method = RequestMethod.GET)
    public CartInfo cart() {
        return cart.getInfo();
    }
}
