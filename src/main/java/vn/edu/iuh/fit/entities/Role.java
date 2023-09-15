package vn.edu.iuh.fit.entities;

import java.util.Objects;

public class Role {
    private String role_id;
    private String role_name;
    private String description;
    private short status;

    public Role(String role_id) {
        this.role_id = role_id;
    }

    public String role_id() {
        return role_id;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short status() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String role_name() {
        return role_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }


}
