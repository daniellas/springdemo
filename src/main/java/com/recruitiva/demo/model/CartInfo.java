package com.recruitiva.demo.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartInfo {
    BigDecimal value = BigDecimal.ZERO;
    long count;
    Map<Long, CartItem> articles = new HashMap<Long, CartItem>();

    public CartInfo() {
        value.setScale(2);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Map<Long, CartItem> getArticles() {
        return articles;
    }

    public void setArticles(Map<Long, CartItem> articles) {
        this.articles = articles;
    }
}
