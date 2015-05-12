package com.recruitiva.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitiva.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
