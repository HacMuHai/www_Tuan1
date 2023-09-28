package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AccountService {
    public int login(String id,String password) throws SQLException;
    public boolean update(Account account) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public Map<Account,String> getAll() throws SQLException;
    public Account getOne(String id) throws SQLException;
    public boolean insert(Account account) throws SQLException;
    public int isAdmin(String acountID) throws SQLException;

}
