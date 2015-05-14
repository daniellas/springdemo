package com.recruitiva.demo.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.recruitiva.demo.entity.Article;

public class CartTest {

    @Test
    public void addSingleItemShouldSetQuantityAndValue() {
        Cart cart = new SessionCart();
        Article article = new Article();

        article.setId(1l);
        article.setName("x");
        article.setPrice(new BigDecimal(10l));
        cart.addArticle(article);

        Assert.assertEquals(new BigDecimal("10.00"), cart.getContent().getValue());
        Assert.assertEquals(1, cart.getContent().getQuantity());
    }

    @Test
    public void addMultipleItemShouldSetQuantityAndValue() {
        Cart cart = new SessionCart();
        Article article = new Article();

        article.setId(1l);
        article.setName("x");
        article.setPrice(new BigDecimal(10l));
        cart.addArticle(article);

        article = new Article();
        article.setId(2l);
        article.setName("y");
        article.setPrice(new BigDecimal(20l));
        cart.addArticle(article);

        Assert.assertEquals(new BigDecimal("30.00"), cart.getContent().getValue());
        Assert.assertEquals(2, cart.getContent().getQuantity());
    }

    @Test
    public void addRemoveItemShouldSetQuantityAndValue() {
        Cart cart = new SessionCart();
        Article article = new Article();

        article.setId(1l);
        article.setName("x");
        article.setPrice(new BigDecimal(10l));
        cart.addArticle(article);

        article = new Article();
        article.setId(2l);
        article.setName("y");
        article.setPrice(new BigDecimal(20l));
        cart.addArticle(article);

        cart.removeArticle(2l);
        Assert.assertEquals(new BigDecimal("10.00"), cart.getContent().getValue());
        Assert.assertEquals(1, cart.getContent().getQuantity());
    }

    @Test
    public void purgeShouldSetZeroQuantityAndValue() {
        Cart cart = new SessionCart();
        Article article = new Article();

        article.setId(1l);
        article.setName("x");
        article.setPrice(new BigDecimal(10l));
        cart.addArticle(article);

        article = new Article();
        article.setId(2l);
        article.setName("y");
        article.setPrice(new BigDecimal(20l));
        cart.addArticle(article);

        cart.purge();
        Assert.assertEquals(new BigDecimal("0"), cart.getContent().getValue());
        Assert.assertEquals(0, cart.getContent().getQuantity());
    }
}
