package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountService;

import java.sql.SQLException;
import java.util.List;

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
    public List<Account> getAll() throws SQLException {
        return accountRepository.getAll();
    }

    @Override
    public Account getOne(String id) throws SQLException {
        return accountRepository.getOne(id);
    }

    @Override
    public boolean update(Account account) throws SQLException {
        return accountRepository.update(account);
    }

    @Override
    public boolean isAdmin(String acountID) throws SQLException {
        return accountRepository.isAdmin(acountID);
    }


}
