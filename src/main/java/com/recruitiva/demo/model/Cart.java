package com.recruitiva.demo.model;

import org.springframework.transaction.annotation.Transactional;

import com.recruitiva.demo.entity.Article;

@Transactional
public interface Cart {

    CartInfo getInfo();

    CartInfo addArticle(Article article);
    
    CartInfo removeArticle(Long id);
}
