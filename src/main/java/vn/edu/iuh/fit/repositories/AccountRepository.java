package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository{
    private Connection connection;

    public  AccountRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public boolean insert(Account account){
        String sql = "INSERT account VALUE(?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,account.getAccount_id());
            statement.setString(2,account.getFull_name());
            statement.setString(3,account.getPassword());
            statement.setString(4,account.getEmail());
            statement.setString(5,account.getPhone());
            statement.setInt(6,account.getStatus());

            return  statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Account account){
        String sql = "UPDATE account\n" +
                "SET full_name=?,password = ?, email = ?, phone = ?, `status` = ?\n" +
                "WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,account.getFull_name());
            statement.setString(2,account.getPassword());
            statement.setString(3,account.getEmail());
            statement.setString(4,account.getPhone());
            statement.setInt(5,account.getStatus());
            statement.setString(6,account.getAccount_id());

            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
           e.printStackTrace();
           return false;
        }
    }

    public boolean delete(String id){
        String sql = "DELETE account WHERE account_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,id);
            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
