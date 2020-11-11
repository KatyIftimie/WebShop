package com.codecool.shop.data;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.UserInfoDaoMem;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance = null;

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        if (instance==null){
            instance = new DbConnection();
        }
        return instance;
    }

    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        // TODO: update database parameters
        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("katy");
        dataSource.setPassword("jackie");
        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

}
