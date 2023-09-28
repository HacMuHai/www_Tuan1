package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AccountServiceImp implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImp() throws SQLException, ClassNotFoundException {
        accountRepository = new AccountRepository();
    }

    @Override
    public int login(String id, String password) throws SQLException {
        return accountRepository.login(id, password);
    }

    @Override
    public Map<Account,String> getAll() throws SQLException {
        return accountRepository.getAll();
    }

    @Override
    public Account getOne(String id) throws SQLException {
        return accountRepository.getOne(id);
    }

    @Override
    public boolean insert(Account account) throws SQLException {
        return accountRepository.insert(account);
    }

    @Override
    public boolean update(Account account) throws SQLException {
        return accountRepository.update(account);
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return accountRepository.delete(id);
    }

    @Override
    public int isAdmin(String acountID) throws SQLException {
        return accountRepository.isAdmin(acountID);
    }


}
