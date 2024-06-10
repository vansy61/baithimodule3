package com.example.baithiketthuc.repositories;

import com.example.baithiketthuc.database.DBConnect;
import com.example.baithiketthuc.models.Category;
import com.example.baithiketthuc.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryRepo implements ICategoryRepo {
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = new DBConnect().getConnection();
        String sql = "select * from categories";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString("name"));
                category.setId(rs.getInt("id"));
                categories.add(category);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
