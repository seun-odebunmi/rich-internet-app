package com.classwork.ecom.repository;

import com.classwork.ecom.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContaining(String name, Pageable pageable);

    Page<Product> findByPriceEquals(double price, Pageable pageable);

    Page<Product> findByNameContainingAndPriceEquals(String name, Double price, Pageable pageable);
}
