package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserInfoDao;
import com.codecool.shop.model.UserInfo;
import java.sql.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoMem implements UserInfoDao {
    private List<UserInfo> data = new ArrayList<>();
    private static UserInfoDaoMem instance = null;
    private DataSource dataSource;

    private UserInfoDaoMem(DataSource dataSource){ this.dataSource=dataSource;};


    public static UserInfoDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserInfoDaoMem(dataSource);
        }
        return instance;
    }


    @Override
    public void add(UserInfo user) {
        Connection conn = null;
        Statement stmt = null;
        List<UserInfo> tempList = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String emailAddress = rs.getString("email_address");
                String billingAddress = rs.getString("billing_address");
                String shippingAddress = rs.getString("shiping");


                UserInfo tempUser = new UserInfo(id,firstName,lastName,emailAddress,billingAddress,shippingAddress);
                tempList.add(tempUser);
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(stmt!= null){
                    conn.close();
                }
            }catch (SQLException se){

            }try {
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException se){

            }
        }
        for(UserInfo x:tempList){
            System.out.println("this is tempList: " + x.getFirstName() + "  " + x.getLastName());
        }


    }

    @Override
    public UserInfo find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<UserInfo> getAll() {
        return data;
    }
}
