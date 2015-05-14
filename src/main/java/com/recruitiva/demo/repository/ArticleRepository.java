package com.recruitiva.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);

    @Query("select a from Article a where a.name like %:name%")
    List<Article> search(@Param("name") String name);
}
