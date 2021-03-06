package com.recruitiva.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.model.Cart;
import com.recruitiva.demo.model.CartContent;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    Cart cart;

    @RequestMapping(method = RequestMethod.GET)
    public CartContent cart() {
        return cart.getContent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CartContent addArticle(@RequestBody Article article) {
        return cart.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public CartContent removeArticle(@RequestParam Long id) {
        return cart.removeArticle(id);
    }
}
