package com.classwork.ecom.util;

import com.classwork.ecom.entity.Product;
import com.classwork.ecom.service.ProductService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ProductUtil {

    @Autowired
    private ProductService productService;

    public void generateProduct(int index) {
        Faker faker = new Faker(new Random(index));
        Product product = new Product();
        product.setName(faker.commerce().productName());
        product.setPrice(faker.number().randomDouble(2, 1, 999999));
        product.setDescription(faker.lorem().sentence());
        product.setQuantity(faker.number().randomDigitNotZero());

        productService.createProduct(product);
    }

    public void generateSampleProducts(int number) {
        long count = productService.productsCount();

        if (count < 1) {
            for (int i = 0; i < number; i++) {
                generateProduct(i);
            }
        } else if (count > 0 && count < number) {
            for (int i = 0; i < (number - count); i++) {
                generateProduct(i);
            }
        }
    }
}
