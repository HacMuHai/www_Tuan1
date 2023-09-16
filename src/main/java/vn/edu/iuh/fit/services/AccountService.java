package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    public int login(String id,String password) throws SQLException;
    public List<Account> getAll() throws SQLException;
    public Account getOne(String id) throws SQLException;
    public boolean isAdmin(String acountID) throws SQLException;

}
