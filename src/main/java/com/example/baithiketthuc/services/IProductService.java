package com.example.baithiketthuc.services;

import com.example.baithiketthuc.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    void insert(Product product);
    Product findProductById(int id);

    void update(Product product);

    void delete(int id);

    List<Product> searchProductByNameAndPrice(String name, Double price);
}
