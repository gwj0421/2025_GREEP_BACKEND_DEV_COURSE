package step.two_three.domain;

import java.time.LocalDateTime;

public class AccountDto {
    private String id;
    private String pw;
    private String name;
    private String nickname;
    private String email;
    private LocalDateTime createAt;

    public AccountDto(String id, String pw, String name, String nickname, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public AccountDto(String pw, String email) {
        this.pw = pw;
        this.email = email;
    }

    public AccountDto(String id, String email, LocalDateTime createAt) {
        this.id = id;
        this.email = email;
        this.createAt = createAt;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
