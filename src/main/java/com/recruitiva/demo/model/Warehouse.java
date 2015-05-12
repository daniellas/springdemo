package com.recruitiva.demo.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

@Transactional
@Validated
public interface Warehouse {
    List<Category> categories();

    @Secured({ "ROLE_ADMIN" })
    Category saveCategory(@Valid Category category);

    List<Article> articles();

    List<Article> articles(Long categoryId);

    @Secured({ "ROLE_ADMIN" })
    Article saveArticle(@Valid Article article);
}
