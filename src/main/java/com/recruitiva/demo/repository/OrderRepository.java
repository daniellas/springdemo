package com.recruitiva.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitiva.demo.entity.ShopOrder;

public interface OrderRepository extends JpaRepository<ShopOrder, Long> {
}
