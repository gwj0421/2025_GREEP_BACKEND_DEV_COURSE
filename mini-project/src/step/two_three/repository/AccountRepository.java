package step.two_three.repository;

import step.two_three.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static step.two_three.domain.AccountDto.*;

public class AccountRepository {
    private List<Account> repository;
    private Long primaryKey;

    public AccountRepository() {
        this.repository = new ArrayList<>();
        this.primaryKey = 0L;
    }

    public void create(AccountDto accountDto) {
        repository.add(new Account(primaryKey++, accountDto.getId(), accountDto.getPw(), accountDto.getName(), accountDto.getNickname(), accountDto.getEmail(), Role.USER));
    }

    public Optional<Session> attemptLogin(LoginForm loginForm) {
        for (Account account : repository) {
            if (account.getId().equals(loginForm.getId()) && account.getPw().equals(loginForm.getPw())) {
                return Optional.of(new Session(account.getAccountId(), account.getName(), account.getRole()));
            }
        }
        return Optional.empty();
    }

    public Optional<AccountDto> read(Long accountId) {
        for (Account account : repository) {
            if (account.getAccountId().equals(accountId)) {
                return Optional.of(
                        AccountDtoBuilder.builder()
                                .id(account.getId())
                                .email(account.getEmail())
                                .createAt(account.getCreatedAt())
                                .build()
                );
            }
        }
        return Optional.empty();
    }

    public boolean update(Long accountId, AccountDto accountDto) {
        for (Account account : repository) {
            if (account.getAccountId().equals(accountId)) {
                account.update(accountDto);
                return true;
            }
        }
        return false;
    }

    public boolean exist(Long accountId) {
        for (Account account : repository) {
            if (account.getAccountId().equals(accountId)) {
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long accountId) {
        for (Account account : repository) {
            if (account.getAccountId().equals(accountId)) {
                repository.remove(account);
                return true;
            }
        }
        return false;
    }
}
