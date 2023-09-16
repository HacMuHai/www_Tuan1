package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Log;

public interface LogService {
    public int insert(String accountID, String note);
    public boolean update(Long id, String note);
}
