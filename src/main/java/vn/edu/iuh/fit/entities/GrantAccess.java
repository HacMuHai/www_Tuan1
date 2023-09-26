package vn.edu.iuh.fit.entities;

import java.util.Objects;

public class GrantAccess {
    private  String roleID;
    private  String accountID;
    private  IsGrant isGrant;
    private String note;

    public GrantAccess(){

    }

    public GrantAccess(String roleID, String accountID, IsGrant isGrant, String note) {
        this.roleID = roleID;
        this.accountID = accountID;
        this.isGrant = isGrant;
        this.note = note;
    }

    public String getRoleIDd() {
        return roleID;
    }

    public String getAccountID() {
        return accountID;
    }

    public int getGrant() {
        return isGrant.getValue();
    }

    public void setIsGrant(IsGrant isGrant) {
        this.isGrant = isGrant;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Grant_access{" +
                "role_id='" + roleID + '\'' +
                ", account_id='" + accountID + '\'' +
                ", is_grant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccess that = (GrantAccess) o;
        return Objects.equals(roleID, that.roleID) && Objects.equals(accountID, that.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, accountID);
    }
}
