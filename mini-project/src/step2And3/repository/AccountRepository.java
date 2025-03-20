package step2And3.repository;

import step2And3.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if (account.getAccountId() == accountId) {
                return Optional.of(new AccountDto(account.getId(), account.getEmail(), account.getCreatedAt()));
            }
        }
        return Optional.empty();
    }

    public boolean update(Long accountId, AccountDto accountDto) {
        for (Account account : repository) {
            if (account.getAccountId() == accountId) {
                account.update(accountDto);
                return true;
            }
        }
        return false;
    }

    public boolean exist(Long accountId) {
        for (Account account : repository) {
            if (account.getAccountId() == accountId) {
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long accountId) {
        for (Account account : repository) {
            if (account.getAccountId() == accountId) {
                repository.remove(account);
                return true;
            }
        }
        return false;
    }
}
