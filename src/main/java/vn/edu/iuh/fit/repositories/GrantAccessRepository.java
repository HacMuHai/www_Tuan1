package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.GrantAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GrantAccessRepository {
    private Connection connection;

    public GrantAccessRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
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

    public boolean delete(String id) {
        String sql = "DELETE FROM grant_access WHERE account_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
