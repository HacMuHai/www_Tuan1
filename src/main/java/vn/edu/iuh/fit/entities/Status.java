package vn.edu.iuh.fit.entities;

public enum Status {
    active(1), deactive(0), delete(-1);
    private int value;
    Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
