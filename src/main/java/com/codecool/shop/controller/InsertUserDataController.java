package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserInfoDaoMem;
import com.codecool.shop.data.DbConnection;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.UserInfo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/payment"})
public class InsertUserDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("shoppingCart/payment.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbConnection dataSource = DbConnection.getInstance();
        UserInfoDaoMem userInfoDataStore = null;
        try {
            userInfoDataStore = UserInfoDaoMem.getInstance(dataSource.connect());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        UserInfo thisUser = new UserInfo();

        String firstName =  req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String billingAddress = req.getParameter("bilingAddress");
        String shippingAddress = req.getParameter("shippingAddress");

        System.out.println("FN: "+ firstName + " LN: " + lastName + " em: "+ email + " PHN: " +phoneNumber+ " BA: " + billingAddress + " SA: " +shippingAddress );

        thisUser.setFirstName(firstName);
        thisUser.setLastName(lastName);
        thisUser.setEmail(email);
        thisUser.setPhoneNumber(phoneNumber);
        thisUser.setBilingAddress(billingAddress);
        thisUser.setShippingAddress(shippingAddress);
        userInfoDataStore.add(thisUser);
        resp.sendRedirect("/choosePayment");

    }
}