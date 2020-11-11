package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
    public class ShoppingCartController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ProductDao productDataStore = ProductDaoMem.getInstance();
            CartDao orderDataStore = CartDaoMem.getInstance();
            Cart thisCart = orderDataStore.find(1);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("order", productDataStore.find(1));
            context.setVariable("cart",thisCart.getItems());
            context.setVariable("discard", thisCart);
            context.setVariable("total", thisCart.getTotal());

            context.setVariable("quantity", thisCart.getQuantity());
//            context.setVariable("order", productDataStore.find(0));

            engine.process("shoppingCart/cartView.html", context, resp.getWriter());
        }
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String newQuantity = req.getParameter("quantity");
            String id = req.getParameter("id");
            CartDao orderDataStore = CartDaoMem.getInstance();
            Cart thisCart = orderDataStore.find(1);
            thisCart.changeQuantity(id,newQuantity);
            resp.sendRedirect("cart");

        }
    }


