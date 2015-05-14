package com.recruitiva.demo.model;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.repository.ArticleRepository;

public class DefaultCart implements Cart {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCart.class);

    CartContent content = new CartContent();

    ArticleRepository articleRepo;

    public void init() {
        LOGGER.info("Initializing Cart");
    }

    @Override
    public CartContent getContent() {
        return content;
    }

    @Override
    public CartContent addArticle(Article article) {
        Long articleId = article.getId();

        if (content.getArticles().containsKey(articleId)) {
            CartItem item = content.getArticles().get(articleId);

            item.quantity++;
            item.setValue(article.getPrice().multiply(new BigDecimal(item.getQuantity())));
        } else {
            CartItem item = new CartItem();

            item.setArticle(article);
            item.setQuantity(1l);
            item.setValue(article.getPrice());

            content.getArticles().put(articleId, item);
        }

        recalculate();

        return content;
    }

    @Override
    public CartContent removeArticle(Long id) {
        content.getArticles().remove(id);
        recalculate();

        return content;
    }

    private void recalculate() {
        BigDecimal totalValue = BigDecimal.ZERO;
        Long count = 0l;

        totalValue.setScale(2);
        for (Map.Entry<Long, CartItem> item : content.getArticles().entrySet())
        {
            totalValue = totalValue.add(item.getValue().getValue());
            count += item.getValue().getQuantity();
        }

        content.setValue(totalValue);
        content.setQuantity(count);
    }

    @Override
    public CartContent purge() {
        content.getArticles().clear();
        recalculate();
        
        return content;
    }
}
