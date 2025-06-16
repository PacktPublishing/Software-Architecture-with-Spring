package com.packtpub.onlineauction.repository;

import com.packtpub.onlineauction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
