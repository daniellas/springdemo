package com.recruitiva.demo.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartContent {
    BigDecimal value = BigDecimal.ZERO;
    long quantity;
    Map<Long, CartItem> articles = new HashMap<Long, CartItem>();

    public CartContent() {
        value.setScale(2);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Map<Long, CartItem> getArticles() {
        return articles;
    }

    public void setArticles(Map<Long, CartItem> articles) {
        this.articles = articles;
    }
}
