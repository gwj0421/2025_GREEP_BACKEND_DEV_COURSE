package step_2_3.service;

import step_2_3.domain.*;

import java.util.Optional;

public interface AccountService {
    void signup(AccountDto accountDto);

    Session signIn(Session user, LoginForm loginForm);

    Optional<AccountDto> detail(Long accountId);

    boolean exist(Long accountId);

    boolean edit(Long accountId,AccountDto accountDto);

    boolean remove(Long accountId);

    boolean authenticate(Request request, Role role);
}
