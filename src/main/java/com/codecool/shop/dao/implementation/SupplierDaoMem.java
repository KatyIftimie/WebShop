package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    private DataSource dataSource;
    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem(DataSource dataSource) { this.dataSource = dataSource; }

    public static SupplierDaoMem getInstance(DataSource source) {
        if (instance == null) {
            instance = new SupplierDaoMem(source);
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO supplier (supplier_name, supplier_description) VALUES ('" + supplier.getName() +"','"  + supplier.getDescription() + "')";
            stmt.executeUpdate(sql);
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
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

            }
        }
    }

    @Override
    public Supplier find(int id) {
        Connection conn = null;
        Statement stmt = null;
        Supplier temp = null;
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql= "SELECT * FROM supplier " +
                    "WHERE id =" + id ;
            ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()){
                 int  idSup = rs.getInt("id");
                 String name = rs.getString("supplier_name");
                 String description = rs.getString("supplier_description");

                 temp = new Supplier(name, description);
                 temp.setId(idSup);

             }
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(stmt != null)
                    conn.close();
            }catch (SQLException se){

            }try {
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println(temp);
        return temp;
    }

    @Override
    public void remove(int id) {
        // TO DO
        System.out.println("!!!!!!!!!!!  SUPLIER REMOVE !!!!!");
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        Connection conn = null;
        Statement stm = null;
        List<Supplier>tempList = new ArrayList<>();
        try{

            conn = dataSource.getConnection();
            stm = conn.createStatement();
            String sql ="SELECT * FROM supplier";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("supplier_name");
                String lastName = rs.getString("supplier_description");

                Supplier temporar = new Supplier(firstName,lastName);
                temporar.setId(id);
                tempList.add(temporar);
            }
            rs.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(stm != null)
                    conn.close();
            }catch (Exception se){

            }try {
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return tempList;
    }


}
