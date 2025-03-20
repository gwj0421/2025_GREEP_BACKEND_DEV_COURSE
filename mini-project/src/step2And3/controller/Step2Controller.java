package step2And3.controller;

import base.exception.IncorrectInputException;
import base.utils.StringUtils;
import base.utils.UrlUtils;
import step2And3.domain.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static base.config.Config.*;

public class Step2Controller {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Request request = new Request();

        while (true) {
            try {
                String originUrl = step2View.inputCommand();
                request.setUrl(UrlUtils.makeUrl(originUrl));
                switch (request.getUrl().getCategory()) {
                    case "boards" -> {
                        switch (request.getUrl().getFunction()) {
                            case "add" -> {
                                if (!step2AccountService.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                String boardName = step2View.create();
                                step2BoardService.create(boardName, request.getSession().getAccountId());
                            }
                            case "edit" -> {
                                if (!step2AccountService.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean existResult = step2BoardService.exist(boardId);
                                if (!step2View.checkBoard(boardId, existResult)) {
                                    continue;
                                }
                                String boardName = step2View.create();
                                boolean result = step2BoardService.update(boardId, boardName);
                                step2View.checkUpdateBoard(boardId, result);
                            }
                            case "remove" -> {
                                if (!step2AccountService.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean result = step2BoardService.delete(boardId);
                                step2View.deleteBoard(boardId, result);
                            }
                            case "view" -> {
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                List<PostDto> postsInBoard = step2BoardService.findAllByBoardId(boardId);
                                step2View.printPostsInBoard(postsInBoard);
                            }
                            default -> step2View.showInvalidInput();
                        }
                    }
                    case "posts" -> {
                        switch (request.getUrl().getFunction()) {
                            case "add" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean existResult = step2BoardService.exist(boardId);
                                if (!step2View.checkBoard(boardId, existResult)) {
                                    continue;
                                }
                                PostDto postDto = step2View.inputPostDto(boardId);
                                step2PostService.add(boardId, postDto, request.getSession().getAccountId());
                            }
                            case "remove" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                boolean result = step2PostService.remove(postId);
                                step2View.deletePost(postId, result);
                            }
                            case "edit" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                boolean existResult = step2PostService.exist(postId);
                                if (!step2View.checkPost(postId, existResult)) {
                                    continue;
                                }
                                PostDto postDto = step2View.updatePostDto();
                                step2PostService.edit(postId, postDto);
                            }
                            case "view" -> {
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                Optional<PostDto> postDto = step2PostService.view(postId);
                                step2View.printPost(postId, postDto);
                            }
                            default -> step2View.showInvalidInput();
                        }
                    }
                    case "accounts" -> {
                        switch (request.getUrl().getFunction()) {
                            case "signup" -> {
                                if (!step2AccountService.authenticate(request, Role.VISITOR)) {
                                    continue;
                                }
                                AccountDto accountDto = step2View.inputAccountDto();
                                step2AccountService.signup(accountDto);
                            }
                            case "signin" -> {
                                if (!step2AccountService.authenticate(request, Role.VISITOR)) {
                                    continue;
                                }
                                LoginForm loginForm = step2View.inputLoginForm();
                                request.setSession(step2AccountService.signIn(request.getSession(), loginForm));
                                step2View.login(request.getSession());
                            }
                            case "signout" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                request.setSession(step2View.signOut(request.getSession()));
                            }
                            case "detail" -> {
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                Optional<AccountDto> accountDto = step2AccountService.detail(accountId);
                                step2View.detail(accountId, accountDto);
                            }
                            case "edit" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                boolean existResult = step2AccountService.exist(accountId);
                                if (!step2View.checkAccount(accountId, existResult)) {
                                    continue;
                                }
                                AccountDto updateAccount = step2View.inputUpdateAccount();
                                boolean editResult = step2AccountService.edit(accountId, updateAccount);
                                step2View.updateAccount(accountId, editResult);
                            }
                            case "remove" -> {
                                if (!step2AccountService.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                boolean removeResult = step2AccountService.remove(accountId);
                                step2View.deleteAccount(accountId, removeResult);
                            }
                            default -> step2View.showInvalidInput();
                        }
                    }
                    default -> step2View.showInvalidInput();
                }
            } catch (IncorrectInputException | IOException e) {
                step2View.showInvalidInput();
            }
        }
    }
}
