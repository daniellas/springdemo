package com.recruitiva.demo.model;

import java.math.BigDecimal;

import com.recruitiva.demo.entity.Article;

public class CartItem {
    Article article;
    long quantity;
    BigDecimal value;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
