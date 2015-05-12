package com.recruitiva.demo.model;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.repository.ArticleRepository;

public class SessionCart implements Cart {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCart.class);

    CartInfo info = new CartInfo();

    @Autowired
    ArticleRepository articleRepo;

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing Cart");
    }

    @Override
    public CartInfo getInfo() {
        return info;
    }

    @Override
    public CartInfo addArticle(Article article) {
        Long articleId = article.getId();

        if (info.getArticles().containsKey(articleId)) {
            CartItem item = info.getArticles().get(articleId);

            item.quantity++;
            item.setPrice(article.getPrice().multiply(new BigDecimal(item.getQuantity())));
        } else {
            CartItem item = new CartItem();

            item.setArticle(article);
            item.setQuantity(1l);
            item.setPrice(article.getPrice());

            info.getArticles().put(articleId, item);
        }

        recalculate();

        return info;
    }

    @Override
    public CartInfo removeArticle(Long id) {
        info.getArticles().remove(id);
        recalculate();

        return info;
    }

    private void recalculate() {
        BigDecimal totalValue = BigDecimal.ZERO;
        Long count = 0l;

        totalValue.setScale(2);
        for (Map.Entry<Long, CartItem> item : info.getArticles().entrySet())
        {
            totalValue = totalValue.add(item.getValue().getPrice());
            count += item.getValue().getQuantity();
        }

        info.setValue(totalValue);
        info.setCount(count);
    }
}
