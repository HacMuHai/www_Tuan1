package vn.edu.iuh.fit.entities;

import java.util.Date;
import java.util.Objects;

public class Log {
    private long id;
    private String accountId;
    private Date loginTime;
    private Date logoutTime;
    private String notes;

    public Log(long id, String account_id) {
        this.id = id;
        this.accountId = account_id;
    }

    public Log(long id, String accountId, Date loginTime, Date logoutTime, String notes) {
        this.id = id;
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", account_id='" + accountId + '\'' +
                ", login_time=" + loginTime +
                ", logout_time=" + logoutTime +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return id == log.id && Objects.equals(accountId, log.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId);
    }
}
