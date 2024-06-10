package com.example.baithiketthuc.models;

import java.util.HashMap;

public class Product {
    private int id;
    private String name;
    private double price = 10000000;
    private int quantity = 1;
    private String color = "Đỏ";
    private String description;
    private int categoryId;
    private Category category;

    private HashMap<String, String> errors = new HashMap<>();

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void addError(String key, String value) {
        errors.put(key, value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
