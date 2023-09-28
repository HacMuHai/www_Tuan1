package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.services.GrantAccessService;

import java.sql.SQLException;
import java.util.List;

public class GrantAccessServiceImp implements GrantAccessService {
    private GrantAccessRepository grantAccessRepository ;

    public GrantAccessServiceImp() throws SQLException, ClassNotFoundException {
       grantAccessRepository = new GrantAccessRepository();
    }

    @Override
    public GrantAccess getOne(String id) throws SQLException {
        return grantAccessRepository.getOne(id);
    }

    @Override
    public List<GrantAccess> getAll() throws SQLException {
        return grantAccessRepository.getAll();
    }

    @Override
    public boolean insert(GrantAccess grantAccess) {
        return grantAccessRepository.insert(grantAccess);
    }

    @Override
    public boolean update(GrantAccess grantAccess) {
        return grantAccessRepository.update(grantAccess);
    }

    @Override
    public boolean changeRole(GrantAccess grantAccess) {
        return grantAccessRepository.changeRole(grantAccess);
    }

    @Override
    public boolean delete(String id) {
        return grantAccessRepository.delete(id);
    }
}
