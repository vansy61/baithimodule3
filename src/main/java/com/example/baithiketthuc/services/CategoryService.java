package com.example.baithiketthuc.services;

import com.example.baithiketthuc.models.Category;
import com.example.baithiketthuc.repositories.CategoryRepo;
import com.example.baithiketthuc.repositories.ICategoryRepo;

import java.util.Collections;
import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryRepo categoryRepo = new CategoryRepo();
    @Override
    public List<Category> getAll() {
        return categoryRepo.getAll();
    }
}
