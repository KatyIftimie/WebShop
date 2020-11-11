package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserInfoDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.UserInfoDaoMem;

import com.codecool.shop.data.DbConnection;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.UserInfo;
import com.codecool.shop.util.Util;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

//import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "confirmation", urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req,resp, req.getServletContext());
//
//        CartDao orderDataStore = CartDaoMem.getInstance();
//        Cart thisCart = orderDataStore.find(1);
//        UserInfoDao userInfo = UserInfoDaoMem.getInstance();
//        UserInfo user = userInfo.find(1);
//
//
//
//        context.setVariable("cart", thisCart.getItems());
//        context.setVariable("total", thisCart.getTotal());
//        context.setVariable("date", Util.getDate());
//
//
//        engine.process("payment/confirmation.html", context, resp.getWriter());

        sendEmailConfirmation("marianaiftimie95@gmail.com", "Mos Craciun", 2, "300");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        DbConnection dataSource = DbConnection.getInstance();
        UserInfoDaoMem userInfoDataStore = null;
        try {
            userInfoDataStore = UserInfoDaoMem.getInstance(dataSource.connect());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        UserInfo thisUser = userInfoDaoDataStore.find(1);
        CartDao orderDataStore = CartDaoMem.getInstance();
        Cart thisCart = orderDataStore.find(1);
        UserInfoDao userInfo = null;
        try {
            userInfo = UserInfoDaoMem.getInstance(dataSource.connect());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        UserInfo user = userInfo.find(1);


//        context.setVariable("userFname", thisUser.getFirstName());
//        context.setVariable("userLname", thisUser.getLastName());
//        context.setVariable("phone", thisUser.getPhoneNumber());
//        context.setVariable("bAddress", thisUser.getBilingAddress());
//        context.setVariable("sAddress", thisUser.getShippingAddress());
//        context.setVariable("email", thisUser.getEmail());

        context.setVariable("cart", thisCart.getItems());
        context.setVariable("total", thisCart.getTotal());
        context.setVariable("date", Util.getDate());


        engine.process("payment/confirmation.html", context, resp.getWriter());

    }




    private void sendEmailConfirmation(String custEmail, String fullName, int orderId, String total) {
//        String to = custEmail;
        String to = "marianaiftimie95@gmail.com";
        String from = "ecaterina.iftimie@gmail.com";
        String host = "smtp.gmail.com";
        String subject = "Shop order confirmation";
        String body = "order details";
//        final String user = "ecaterina.iftimie@gmail.com";
//        final String pass = "cracanici666";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("ecaterina.iftimie@gmail.com", "cracanici666");
                    }
                });
//        Session session = Session.getDefaultInstance(props, null);


        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("WEB SHOP ORDER CONFIRMATION");
            message.setText(
                    "Hello " + fullName + ",\n" +
                    "\n" +
                    "Thanks for purchasing from our shop.\n" +
                    "Your order, ID: " + orderId + ", totalling " + total + " USD, was processed successfully");

            //send the message
            System.out.println("SEnding...");
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}