package com.recruitiva.demo.model;

import java.util.List;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;
import com.recruitiva.demo.repository.ArticleRepository;
import com.recruitiva.demo.repository.CategoryRepository;

public class LocalWarehouse implements Warehouse {

    CategoryRepository categoryRepo;

    ArticleRepository articleRepo;

    @Override
    public List<Category> categories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Article> articles() {
        return articleRepo.findAll();
    }

    @Override
    public List<Article> articles(Long categoryId) {
        return articleRepo.findByCategory(categoryRepo.findOne(categoryId));
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepo.save(article);
    }

}
