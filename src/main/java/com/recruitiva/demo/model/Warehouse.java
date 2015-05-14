package com.recruitiva.demo.model;

import java.util.List;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

public interface Warehouse {
    List<Category> categories();

    Category saveCategory(Category category);

    List<Article> articles();

    List<Article> articles(Long categoryId);

    Article saveArticle(Article article);
}
