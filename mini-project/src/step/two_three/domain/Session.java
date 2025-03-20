package step.two_three.domain;

public class Session {
    private Long accountId;
    private String name;
    private Role role;

    public Session() {
        this.name = "임시 방문자";
        this.accountId = null;
        this.role = Role.VISITOR;
    }

    public Session(Long accountId, String name, Role role) {
        this.accountId = accountId;
        this.name = name;
        this.role = role;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
