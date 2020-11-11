package com.codecool.shop.dao;

import com.codecool.shop.model.UserInfo;

import java.util.List;

public interface UserInfoDao {

    void add(UserInfo user);
    UserInfo find(int id);
    void remove(int id);
    List<UserInfo> getAll();


}
