package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.data.DbConnection;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/category/*")
public class CategoryController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
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

        ProductCategory category = productCategoryDataStore.getProductCategory(req.getParameter("category"));
        System.out.println(category);

        context.setVariable("products", productDataStore.getBy(category));
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("supplier", supplierDataStore.find(1));
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("cartSize", thisCart.getNumOfItems());

        System.out.println(productDataStore.getBy("tablet"));

        engine.process("product/index.html", context, resp.getWriter());

    }
}