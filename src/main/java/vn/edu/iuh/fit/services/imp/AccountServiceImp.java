package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountService;

import java.sql.SQLException;

public class AccountServiceImp implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImp() throws SQLException, ClassNotFoundException {
        accountRepository = new AccountRepository();
    }

    @Override
    public int login(String id, String password) throws SQLException {
        return accountRepository.login(id, password);
    }
}
