package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.services.LogService;

import java.sql.SQLException;
import java.util.Date;

public class LogServiceImp implements LogService {

    private LogRepository repository;

    public LogServiceImp() throws SQLException, ClassNotFoundException {
        repository = new LogRepository();
    }


    @Override
    public int insert(String accountID,  String note) {
        return repository.insert(accountID,  note);
    }

    @Override
    public boolean update(Long id,  String note) {
        return repository.update(id,  note);
    }
}
