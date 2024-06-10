package com.example.baithiketthuc.services;

import com.example.baithiketthuc.models.Product;
import com.example.baithiketthuc.repositories.IProductRepo;
import com.example.baithiketthuc.repositories.ProductRepo;

import java.util.Collections;
import java.util.List;

public class ProductService implements IProductService {
    IProductRepo productRepo = new ProductRepo();
    @Override
    public List<Product> getAll() {
        return productRepo.getAll();
    }

    @Override
    public void insert(Product product) {
        validate(product);
        if(product.getErrors().isEmpty()) {
            productRepo.insert(product);
        }
    }

    @Override
    public Product findProductById(int id) {
        return productRepo.findProductById(id);
    }

    @Override
    public void update(Product product) {
        validate(product);
        if(product.getErrors().isEmpty()) {
            productRepo.update(product);
        }
    }

    @Override
    public void delete(int id) {
        productRepo.delete(id);
    }

    @Override
    public List<Product> searchProductByNameAndPrice(String name, Double price) {
        return productRepo.searchProductByNameAndPrice(name, price);
    }

    public void validate(Product product) {
        if (product.getName().isEmpty()) {
            product.addError("name", "Tên không được để trống");
        }

        if(product.getName().length() < 3) {
            product.addError("name", "Tên phải lớn hơn 3 ký tự");
        }

        if(product.getPrice() < 10000000) {
            product.addError("price", "Giá phải lớn hơn 10 triệu");
        }

        if(product.getQuantity() < 1) {
            product.addError("quantity", "Số lượng phải lớn hơn 0");
        }
    }
}
