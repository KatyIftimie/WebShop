package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




@WebServlet(urlPatterns = {"/addtocart"})

public class AddToCartController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("category", productCategoryDataStore.find(1));
//        context.setVariable("categories", productCategoryDataStore.getAll());
//        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//        // // Alternative setting of the template context
//        // Map<String, Object> params = new HashMap<>();
//        // params.put("category", productCategoryDataStore.find(1));
//        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//        // context.setVariables(params);
////        engine.process("product/index.html", context, resp.getWriter());
        CartDao cartDataStore = CartDaoMem.getInstance();

        String productId =  req.getParameter("id");
        Product product= productDataStore.find(Integer.parseInt(productId));
        Cart cart = cartDataStore.find(1);
        cart.addProduct(product);

        resp.sendRedirect("index");
    }
}
