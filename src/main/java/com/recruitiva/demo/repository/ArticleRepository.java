package com.recruitiva.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
}
