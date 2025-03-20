package step.two_three.domain;

import java.time.LocalDateTime;

public class Account extends BaseEntity {
    private Long accountId;
    private String id;
    private String pw;
    private String name;
    private String nickname;
    private String email;
    private Role role;

    public Account(Long accountId, String id, String pw, String name, String nickname, String email, Role role) {
        this.accountId = accountId;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void update(AccountDto accountDto) {
        this.pw = accountDto.getPw();
        this.email = accountDto.getEmail();
        setUpdatedAt(LocalDateTime.now());
    }
}
