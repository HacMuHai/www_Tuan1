package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleRepository {
    private Connection connection;

    public RoleRepository() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getConnectDB().getConnect();
    }

    public boolean insert(Role role) {
        String sql = "INSERT role VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getRoleId());
            statement.setString(2, role.getRoleName());
            statement.setString(3, role.getDescription());
            statement.setInt(4, role.getStatus().getValue());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Role role) {
        String sql = "UPDATE role SET role_name = ?, DESCRIPTION = ?, status = ?\n" +
                "WHERE role_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getRoleName());
            statement.setString(2, role.getDescription());
            statement.setInt(3, role.getStatus().getValue());
            statement.setString(4, role.getRoleId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "UPDATE role SET status = -1\n" +
                "WHERE role_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
