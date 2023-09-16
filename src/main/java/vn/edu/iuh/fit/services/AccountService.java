package vn.edu.iuh.fit.services;

import java.sql.SQLException;

public interface AccountService {
    public int login(String id,String password) throws SQLException;

}
