package com.practice.dummymicroservice.service;

import com.practice.dummymicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Product WHERE name LIKE %:searchStr%", nativeQuery = true)
    List<Product> searchProductByName(@Param("searchStr") String searchStr);
}
