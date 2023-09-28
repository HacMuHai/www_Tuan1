package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.IsGrant;
import vn.edu.iuh.fit.entities.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessRepository {
    private Connection connection;

    public GrantAccessRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public List<GrantAccess> getAll() throws SQLException {
        String sql = "SELECT * FROM grant_access";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<GrantAccess> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new GrantAccess(
                    rs.getString("role_id"),
                    rs.getString("account_id"),
                    IsGrant.fromValue(rs.getInt("is_grant")),
                    rs.getString("note")));
        }
        return list;

    }

    public GrantAccess getOne(String id) throws SQLException {
        String sql = "SELECT * FROM grant_access WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new GrantAccess(
                    rs.getString("role_id"),
                    rs.getString("account_id"),
                    IsGrant.fromValue(rs.getInt("is_grant")),
                    rs.getString("note"));

        }
        return null;
    }

    public boolean insert(GrantAccess grantAccess) {
        String sql = "INSERT grant_access VALUES(?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, grantAccess.getRoleIDd());
            statement.setString(2, grantAccess.getAccountID());
            statement.setInt(3, grantAccess.getGrant());
            statement.setString(4, grantAccess.getNote());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(GrantAccess grantAccess) {
        String sql = "UPDATE grant_access "  +
                " SET is_grant=?, note=?  " +
                " WHERE role_id=? AND account_id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, grantAccess.getGrant());
            statement.setString(2, grantAccess.getNote());
            statement.setString(3, grantAccess.getRoleIDd());
            statement.setString(4, grantAccess.getAccountID());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeRole(GrantAccess grantAccess) {
        String sql = "UPDATE grant_access "  +
                " SET is_grant=?, note=?,role_id = ?  " +
                " WHERE  account_id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, grantAccess.getGrant());
            statement.setString(2, grantAccess.getNote());
            statement.setString(3, grantAccess.getRoleIDd());
            statement.setString(4, grantAccess.getAccountID());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "UPDATE grant_access \n" +
                "SET is_grant = 1\n" +
                "WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
