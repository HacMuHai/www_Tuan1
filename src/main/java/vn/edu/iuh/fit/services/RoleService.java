package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Role;

public interface RoleService {

    public boolean insert(Role role);
    public boolean update(Role role);
    public boolean delete(String id);
}
