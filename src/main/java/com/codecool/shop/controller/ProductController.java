package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.data.DbConnection;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "index", urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbConnection dataSource = DbConnection.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = null;
        try {
            productCategoryDataStore = ProductCategoryDaoDB.getInstance(dataSource.connect());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SupplierDao supplierDataStore = null;
        try {
            supplierDataStore = SupplierDaoMem.getInstance(dataSource.connect());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CartDao orderDataStore = CartDaoMem.getInstance();
        Cart thisCart = orderDataStore.find(1);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("supplier", supplierDataStore.find(11));
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("categories", productCategoryDataStore.getAll());
//        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("products",  productDataStore.getAll());
        context.setVariable("cartSize", thisCart.getNumOfItems());

        System.out.println("??????" + productCategoryDataStore.find(1));
        engine.process("product/index.html", context, resp.getWriter());
    }
}
