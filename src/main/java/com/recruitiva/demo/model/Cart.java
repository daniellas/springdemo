package com.recruitiva.demo.model;

import org.springframework.transaction.annotation.Transactional;

import com.recruitiva.demo.entity.Article;

@Transactional
public interface Cart {

    CartContent getContent();

    CartContent addArticle(Article article);
    
    CartContent removeArticle(Long id);
    
    CartContent purge();
}
