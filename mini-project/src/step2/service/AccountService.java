package step2.service;

import step2.domain.AccountDto;
import step2.domain.LoginForm;
import step2.domain.Session;

import java.util.Optional;

public interface AccountService {
    void signup(AccountDto accountDto);

    Session signIn(Session user, LoginForm loginForm);

    Optional<AccountDto> detail(Long accountId);

    boolean exist(Long accountId);

    boolean edit(Long accountId,AccountDto accountDto);

    boolean remove(Long accountId);
}
