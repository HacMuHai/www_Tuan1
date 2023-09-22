package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public boolean insert(Account account) throws SQLException {
        String sql = "INSERT account VALUE(?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, account.getAccount_id());
        statement.setString(2, account.getFull_name());
        statement.setString(3, account.getPassword());
        statement.setString(4, account.getEmail());
        statement.setString(5, account.getPhone());
        statement.setInt(6, account.getStatus().getValue());

        return statement.executeUpdate() > 0;

    }

    public boolean update(Account account) throws SQLException {
        String sql = "UPDATE account\n" +
                "SET full_name=?,password = ?, email = ?, phone = ?, `status` = ?\n" +
                "WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, account.getFull_name());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getEmail());
        statement.setString(4, account.getPhone());
        statement.setInt(5, account.getStatus().getValue());
        statement.setString(6, account.getAccount_id());

        return statement.executeUpdate() > 0;

    }

    public boolean delete(String id) throws SQLException {
        String sql =  "UPDATE account\n" +
                "SET `status` = -1\n" +
                "WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        return statement.executeUpdate() > 0;

    }

    public List<Account> getAll() throws SQLException {
        String sql = "SELECT * FROM account";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Account> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Account(rs.getString("account_id"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    Status.values()[rs.getInt("status")]));
        }
        return list;

    }

    public Account getOne(String id) throws SQLException {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Account(rs.getString("account_id"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    Status.values()[rs.getInt("status")]);
        }
        return null;

    }


    public int login(String id, String password) throws SQLException {
        Account account = getOne(id);
        if(account == null)
            return 0;
        if (account.getPassword().contentEquals(password)) {
            if (isAdmin(id))
                return 1; // la admin
            return -1; //la user
        } else {
            return 0; // Tai khoan khong ton tai
        }
    }

    public boolean isAdmin(String acountID) throws SQLException {
        String sql = "SELECT * FROM grant_access \n" +
                "\tWHERE account_id = ? AND is_grant = 1 \n" +
                "\t\t\tAND role_id IN (SELECT role_id FROM role WHERE role_name = 'administrator')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, acountID);
        ResultSet rs = statement.executeQuery();
        return rs.next();

    }
}
