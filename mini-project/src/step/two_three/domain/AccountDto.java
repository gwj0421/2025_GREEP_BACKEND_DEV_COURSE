package step.two_three.domain;

import java.time.LocalDateTime;

public class AccountDto {
    private String id;
    private String pw;
    private String name;
    private String nickname;
    private String email;
    private LocalDateTime createAt;

    private AccountDto(String id, String pw, String name, String nickname, String email, LocalDateTime createAt) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.createAt = createAt;
    }

    public static class AccountDtoBuilder implements Builder<AccountDto> {
        private String id;
        private String pw;
        private String name;
        private String nickname;
        private String email;
        private LocalDateTime createAt;

        private AccountDtoBuilder() {
        }

        public static AccountDtoBuilder builder() {
            return new AccountDtoBuilder();
        }

        public AccountDtoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AccountDtoBuilder pw(String pw) {
            this.pw = pw;
            return this;
        }

        public AccountDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AccountDtoBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public AccountDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AccountDtoBuilder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        @Override
        public AccountDto build() {
            return new AccountDto(id, pw, name, nickname, email, createAt);
        }
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
