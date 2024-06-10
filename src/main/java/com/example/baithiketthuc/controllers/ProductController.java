package com.example.baithiketthuc.controllers;

import com.example.baithiketthuc.models.Category;
import com.example.baithiketthuc.models.Product;
import com.example.baithiketthuc.repositories.CategoryRepo;
import com.example.baithiketthuc.repositories.ICategoryRepo;
import com.example.baithiketthuc.services.IProductService;
import com.example.baithiketthuc.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {
    IProductService productService = new ProductService();
    ICategoryRepo categoryRepo = new CategoryRepo();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        switch (url) {
            case "/product/create":
                showFormAddProduct(req, resp);
                break;
            case "/product/update":
                showFormUpdateProduct(req, resp);
                break;
            case "/product/delete":
                deleteProduct(req, resp);
                break;
            default:
                showListProduct(req, resp);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect("/product/list?fromAction=delete");
    }

    private void showFormUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findProductById(id);
        req.setAttribute("product", product);

        List<Category> categories = categoryRepo.getAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        switch (url) {
            case "/product/create":
                createProduct(req, resp);
                break;
            case "/product/update":
                updateProduct(req, resp);
                break;
            default:
                break;
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = new Product();
        product.setId(Integer.parseInt(req.getParameter("id")));
        product.setName(req.getParameter("name"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        product.setColor(req.getParameter("color"));
        product.setDescription(req.getParameter("description"));
        product.setCategoryId(Integer.parseInt(req.getParameter("category_id")));
        productService.update(product);

        if (product.getErrors().isEmpty()) {
            resp.sendRedirect("/product/list?fromAction=update");
        } else {
            List<Category> categories = categoryRepo.getAll();
            req.setAttribute("categories", categories);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        product.setColor(req.getParameter("color"));
        product.setDescription(req.getParameter("description"));
        product.setCategoryId(Integer.parseInt(req.getParameter("category_id")));

        productService.insert(product);

        if (product.getErrors().isEmpty()) {
            resp.sendRedirect("/product/list?fromAction=create");
        } else {
            List<Category> categories = categoryRepo.getAll();
            req.setAttribute("categories", categories);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
        }
    }

    private void showFormAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        req.setAttribute("product", product);
        List<Category> categories = categoryRepo.getAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String p_price = req.getParameter("price");
        Double price = null;
        if(p_price != null) {
            try {
                price = Double.parseDouble(p_price);
            }catch (NumberFormatException e) {
                System.out.println("Invalid price");
            }
        }


        List<Product> products;
        if(name == null && price == null) {
            products = productService.getAll();
        }else {
            products = productService.searchProductByNameAndPrice(name, price);
        }

        req.setAttribute("name", name);
        req.setAttribute("price", price);
        req.setAttribute("products", products);
        req.setAttribute("fromAction", req.getParameter("fromAction"));
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);

    }
}
