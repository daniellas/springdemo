package com.recruitiva.demo.model;

import com.recruitiva.demo.entity.Article;

public interface Cart {

    CartContent getContent();

    CartContent addArticle(Article article);
    
    CartContent removeArticle(Long id);
    
    CartContent purge();
}
