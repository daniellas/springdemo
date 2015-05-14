package com.recruitiva.demo.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.recruitiva.demo.config.ShopConfig;
import com.recruitiva.demo.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ShopConfig.class })
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepo;

    @Test
    public void searchShouldReturnArticles() {
        List<Article> result = articleRepo.search("an");

        Assert.assertFalse(result.isEmpty());
    }
}
