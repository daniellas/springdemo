package com.recruitiva.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.model.Warehouse;

@RestController
@RequestMapping(value = "articles")
public class ArticleController {

    @Autowired
    Warehouse localWarehouse;

    @RequestMapping(method = RequestMethod.GET)
    List<Article> articles(@RequestParam(required = false) Long id) {
        if (id != null) {
            return localWarehouse.articles(id);
        }

        return localWarehouse.articles();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    List<Article> search(@RequestParam String name) {
        return localWarehouse.search(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    Article save(@RequestBody Article article) {
        return localWarehouse.saveArticle(article);
    }

}
