package vn.edu.iuh.fit.entities;

public enum Status {
    ACTIVE(1), DEACTIVE(0), DELETED(-1);
    private int value;
    Status(int value) {
        this.value = value;
    }

    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getStatus() {
        switch (this) {
            case ACTIVE:
                return "ACTIVE";
            case DEACTIVE:
                return "DEACTIVE";
            default:
                return "DELETED";
        }
    }
}
