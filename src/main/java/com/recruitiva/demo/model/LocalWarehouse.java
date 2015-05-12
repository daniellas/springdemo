package com.recruitiva.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitiva.demo.entity.Category;
import com.recruitiva.demo.repository.CategoryRepository;

@Service
public class LocalWarehouse implements Warehouse {

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public List<Category> categories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category add(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category add(String name) {
        Category category = new Category();

        category.setName(name);
        return add(category);
    }

}
