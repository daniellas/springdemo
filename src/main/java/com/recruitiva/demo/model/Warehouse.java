package com.recruitiva.demo.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

@Transactional
@Validated
public interface Warehouse {
    List<Category> categories();

    Category saveCategory(@Valid Category category);

    List<Article> articles();

    List<Article> articles(Long categoryId);

    Article saveArticle(@Valid Article article);
}
