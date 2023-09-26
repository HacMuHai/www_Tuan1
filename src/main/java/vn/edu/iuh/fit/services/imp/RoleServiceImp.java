package vn.edu.iuh.fit.services.imp;

import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.RoleRepository;
import vn.edu.iuh.fit.services.RoleService;

import java.sql.SQLException;

public class RoleServiceImp implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImp() throws SQLException, ClassNotFoundException {
        roleRepository = new RoleRepository();
    }

    @Override
    public boolean insert(Role role) {
        return roleRepository.insert(role);
    }

    @Override
    public boolean update(Role role) {
        return roleRepository.update(role);
    }

    @Override
    public boolean delete(String id) {
        return roleRepository.delete(id);
    }
}
