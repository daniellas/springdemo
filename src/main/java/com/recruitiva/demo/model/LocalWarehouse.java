package com.recruitiva.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;
import com.recruitiva.demo.repository.ArticleRepository;
import com.recruitiva.demo.repository.CategoryRepository;

@Service
public class LocalWarehouse implements Warehouse {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
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
