package com.classwork.ecom.controller;

import com.classwork.ecom.entity.Product;
import com.classwork.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/")
    public ResponseEntity createProduct(@Valid @RequestBody Product newProduct) {
        Product product = productService.createProduct(newProduct);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/")
    public ResponseEntity updateProduct(@Valid @RequestBody Product newProduct) {
        if (newProduct.getId() != null) {
            productService.updateProduct(newProduct);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Product product = productService.createProduct(newProduct);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/")
    public Page<Product> getProducts(Pageable pageable, @RequestParam Optional<String> name, @RequestParam Optional<Double> price) {
        return productService.getProducts(pageable, name, price);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/")
    public void deleteProducts() {
        productService.deleteProducts();
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable int productId) {
        productService.deleteProductById(productId);
    }
}
