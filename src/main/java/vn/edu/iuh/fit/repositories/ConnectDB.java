package vn.edu.iuh.fit.repositories;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection con;
    private static  ConnectDB connectDB;

    public ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb", "root", "sapassword");
        System.out.println("Connect successfull....");
    }

    public static ConnectDB getConnectDB() throws SQLException, ClassNotFoundException {
        if (connectDB == null)
            connectDB = new ConnectDB();
        return  connectDB;
    }

    public Connection getConnect(){
        return con;
    }
}
