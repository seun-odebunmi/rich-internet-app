package com.classwork.ecom.service;

import com.classwork.ecom.entity.Product;
import com.classwork.ecom.exceptions.ItemNotFoundException;
import com.classwork.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Product newProduct) {
        Optional<Product> foundProduct = productRepository.findById(newProduct.getId());
        if (foundProduct.isPresent()) {
            return productRepository.save(newProduct);
        } else {
            throw new ItemNotFoundException("Unable to find product with id: " + newProduct.getId());
        }
    }

    public Page<Product> getProducts(Pageable pageable, Optional<String> name, Optional<Double> price) {
        if (name.isPresent() && price.isPresent()) {
            return productRepository.findByNameAndPrice(name.get(), price.get(), pageable);
        } else if (name.isPresent()) {
            return productRepository.findByNameContaining(name.get(), pageable);
        } else if (price.isPresent()) {
            return productRepository.findByPriceContaining(price.get(), pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    public Product getProductById(long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            return foundProduct.get();
        } else {
            throw new ItemNotFoundException("Unable to find product with id: " + id);
        }
    }

    public long productsCount() {
        return productRepository.count();
    }

    public void deleteProducts() {
        try {
            productRepository.deleteAll();
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to delete products");
        }
    }

    public void deleteProductById(long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to delete product with id: " + id);
        }
    }
}
