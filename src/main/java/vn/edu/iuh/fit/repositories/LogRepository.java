package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogRepository {
    private Connection connection;

    public LogRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public boolean insert(Log log){
        String sql = "INSERT log VALUES(?,?,?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,log.getId());
            statement.setString(2,log.getAccountId());
            statement.setDate(3,convertToSqlDate(log.getLoginTime()));
            statement.setDate(4,convertToSqlDate(log.getLogoutTime()));
            statement.setString(5,log.getNotes());

            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Log log) {
        return false;
    }
    public boolean delete(Long id){
        return false;
    }

    public java.sql.Date convertToSqlDate (java.util.Date date){
        return new Date(date.getTime());
    }

    public java.util.Date convertToUtilDate (java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
