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

public class ProductRepo implements IProductRepo {
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = new DBConnect().getConnection();
        String sql = "select p.*, c.name as category_name from products p join categories c on p.category_id = c.id;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString("category_name"));

                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setColor(rs.getString("color"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategory(category);
                products.add(product);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void insert(Product product) {
        Connection connection = new DBConnect().getConnection();
        String sql = "insert into products(name, price, quantity, color, description, category_id) value(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getColor());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getCategoryId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findProductById(int id) {
        Product product = null;
        Connection connection = new DBConnect().getConnection();
        String sql = "select * from products where id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setColor(rs.getString("color"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return product;
    }

    @Override
    public void update(Product product) {
        Connection connection = new DBConnect().getConnection();
        String sql = "update products set name = ?, price = ?, quantity = ?, color = ?, description = ?, category_id = ? where id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getColor());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getCategoryId());
            ps.setInt(7, product.getId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        Connection connection = new DBConnect().getConnection();
        String sql = "delete from products where id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> searchProductByNameAndPrice(String name, Double price) {
        List<Product> products = new ArrayList<>();
        Connection connection = new DBConnect().getConnection();
        String sql = "select p.*, c.name as category_name from products p join categories c on p.category_id = c.id";
        if (name != null && !name.isEmpty()) {
            sql += " where p.name like '%" + name + "%'";
        }
        if (price != null && price > 0) {
            sql += " and p.price = " + price;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString("category_name"));

                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setColor(rs.getString("color"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategory(category);
                products.add(product);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
