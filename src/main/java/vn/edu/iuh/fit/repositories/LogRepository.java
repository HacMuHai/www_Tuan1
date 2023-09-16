package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Log;

import java.sql.*;

public class LogRepository {
    private Connection connection;

    public LogRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public int getIdCurrent(){
        String sql = "SELECT COUNT(*) FROM log";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int value = resultSet.getInt(1);
                System.out.println(value);
                return value;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int insert(String accountID, String note){
        String sql = "INSERT log(account_id,login_time,notes) VALUES(?,NOW(),?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,accountID);
            statement.setString(2,note);
            if(statement.executeUpdate() > 0)
                return getIdCurrent();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean update(Long id, String note) {
        String sql = "UPDATE log\n" +
                "SET logout_time = NOW(),notes = ?\n" +
                "WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,note);
            statement.setLong(2,id);

            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public java.sql.Date convertToSqlDate (java.util.Date date){
        return new Date(date.getTime());
    }

    public java.util.Date convertToUtilDate (java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
