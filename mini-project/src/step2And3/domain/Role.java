package step2And3.domain;

public enum Role {
    ADMIN("관리자"),
    USER("유저"),
    VISITOR("방문자");
    private String desc;

    Role(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
