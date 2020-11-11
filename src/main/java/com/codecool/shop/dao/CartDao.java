package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

public interface CartDao
{
    void add(Cart item);


    int getSize();
    Cart find(int id);
}
