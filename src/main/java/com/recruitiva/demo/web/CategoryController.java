package com.recruitiva.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruitiva.demo.entity.Category;
import com.recruitiva.demo.model.Warehouse;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    Warehouse localWarehouse;

    @RequestMapping(method = RequestMethod.GET)
    List<Category> categories() {
        return localWarehouse.categories();
    }

    @RequestMapping(method = RequestMethod.POST)
    Category save(@RequestBody Category category) {
        return localWarehouse.saveCategory(category);
    }
}
