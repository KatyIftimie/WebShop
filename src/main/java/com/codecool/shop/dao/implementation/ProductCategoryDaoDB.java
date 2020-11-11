package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDB implements ProductCategoryDao
{
    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoDB instance = null;
    private DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoDB(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public static ProductCategoryDaoDB getInstance(DataSource dataSource)
    {
        if (instance == null) {
            instance = new ProductCategoryDaoDB(dataSource);
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category)
    {
        // TODO:
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategory find(int id)
    {
        Connection conn = null;
        Statement stmt = null;
        ProductCategory temp = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement("SELECT * FROM category WHERE id = ?");
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                int idCat = rs.getInt("id");
                String name = rs.getString("category_name");
                String department = rs.getString("category_department");
                String description = rs.getString("category_description");


                temp = new ProductCategory(name, department, description);
                temp.setId(idCat);

                System.out.println("*****" + idCat + name + department + description);
                System.out.println("22222" + temp.toString());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("xxxxxx" + temp.toString());
        return temp;
    }

    @Override
    public void remove(int id)
    {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll()
    {
        Connection conn = null;
        Statement stmt = null;
        List<ProductCategory> tempList = new ArrayList<>();

        try {
            conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                int idCat = rs.getInt("id");
                String name = rs.getString("category_name");
                String department = rs.getString("category_department");
                String description = rs.getString("category_description");

                ProductCategory temporar = new ProductCategory(name, department, description);
                temporar.setId(idCat);
                tempList.add(temporar);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println(tempList);
        return tempList;
    }

    @Override
    public ProductCategory getProductCategory(String category)
    {
        for (ProductCategory prod : data) {
            if (prod.getName().equals(category)) {
                return prod;
            }
        }
        return null;
    }
}

