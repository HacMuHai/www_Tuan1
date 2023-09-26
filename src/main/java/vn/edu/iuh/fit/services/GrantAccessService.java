package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.GrantAccess;

import java.sql.SQLException;
import java.util.List;

public interface GrantAccessService {
    public GrantAccess getOne(String id) throws SQLException;
    public List<GrantAccess> getAll() throws SQLException;
    public boolean insert(GrantAccess grantAccess);
    public boolean update(GrantAccess grantAccess);
    public boolean delete(String id);
}
