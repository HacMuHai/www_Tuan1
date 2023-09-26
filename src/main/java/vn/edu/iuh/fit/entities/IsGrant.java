package vn.edu.iuh.fit.entities;

public enum IsGrant {
    disable(0), enable(1);

    private int value;

    private IsGrant(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public String getGrant() {
        if(this == disable)
            return "DISABLE";
        return "ENABLE";
    }

    public static IsGrant fromValue(int value) {
        for (IsGrant grant : IsGrant.values()) {
            if (grant.value == value) {
                return grant;
            }
        }
        return null;
    }
}
