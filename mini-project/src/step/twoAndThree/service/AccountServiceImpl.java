package step.twoAndThree.service;

import step.twoAndThree.domain.*;
import step.twoAndThree.repository.AccountRepository;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void signup(AccountDto accountDto) {
        accountRepository.create(accountDto);
    }

    @Override
    public Session signIn(Session user, LoginForm loginForm) {
        if (user.getRole() != Role.VISITOR) {
            return user;
        }
        Optional<Session> loginUser = accountRepository.attemptLogin(loginForm);
        if (loginUser.isEmpty()) {
            return user;
        }
        return loginUser.get();
    }

    @Override
    public Optional<AccountDto> detail(Long accountId) {
        return accountRepository.read(accountId);
    }

    @Override
    public boolean exist(Long accountId) {
        return accountRepository.exist(accountId);
    }

    @Override
    public boolean edit(Long accountId, AccountDto accountDto) {
        return accountRepository.update(accountId, accountDto);
    }

    @Override
    public boolean remove(Long accountId) {
        return accountRepository.delete(accountId);
    }

    @Override
    public boolean authenticate(Request request, Role role) {
        return request.getSession().getRole().equals(role);
    }
}
