package step.two_three.controller;

import base.exception.IncorrectInputException;
import base.utils.StringUtils;
import base.utils.UrlUtils;
import step.two_three.domain.PostDto;
import step.two_three.domain.Role;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static base.config.Config.*;

public class Step2Controller {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        step.two_three.domain.Request request = new step.two_three.domain.Request();

        while (true) {
            try {
                String originUrl = STEP_2_VIEW.inputCommand();
                request.setUrl(UrlUtils.makeUrl(originUrl));
                switch (request.getUrl().getCategory()) {
                    case "boards" -> {
                        switch (request.getUrl().getFunction()) {
                            case "add" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                String boardName = STEP_2_VIEW.create();
                                STEP_2_BOARD_SERVICE.create(boardName, request.getSession().getAccountId());
                            }
                            case "edit" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean existResult = STEP_2_BOARD_SERVICE.exist(boardId);
                                if (!STEP_2_VIEW.checkBoard(boardId, existResult)) {
                                    continue;
                                }
                                String boardName = STEP_2_VIEW.create();
                                boolean result = STEP_2_BOARD_SERVICE.update(boardId, boardName);
                                STEP_2_VIEW.checkUpdateBoard(boardId, result);
                            }
                            case "remove" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean result = STEP_2_BOARD_SERVICE.delete(boardId);
                                STEP_2_VIEW.deleteBoard(boardId, result);
                            }
                            case "view" -> {
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                List<PostDto> postsInBoard = STEP_2_BOARD_SERVICE.findAllByBoardId(boardId);
                                STEP_2_VIEW.printPostsInBoard(postsInBoard);
                            }
                            default -> STEP_2_VIEW.showInvalidInput();
                        }
                    }
                    case "posts" -> {
                        switch (request.getUrl().getFunction()) {
                            case "add" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get("boardId"));
                                boolean existResult = STEP_2_BOARD_SERVICE.exist(boardId);
                                if (!STEP_2_VIEW.checkBoard(boardId, existResult)) {
                                    continue;
                                }
                                PostDto postDto = STEP_2_VIEW.inputPostDto(boardId);
                                STEP_2_POST_SERVICE.add(boardId, postDto, request.getSession().getAccountId());
                            }
                            case "remove" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                boolean result = STEP_2_POST_SERVICE.remove(postId);
                                STEP_2_VIEW.deletePost(postId, result);
                            }
                            case "edit" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                boolean existResult = STEP_2_POST_SERVICE.exist(postId);
                                if (!STEP_2_VIEW.checkPost(postId, existResult)) {
                                    continue;
                                }
                                PostDto postDto = STEP_2_VIEW.updatePostDto();
                                STEP_2_POST_SERVICE.edit(postId, postDto);
                            }
                            case "view" -> {
                                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                                Optional<PostDto> postDto = STEP_2_POST_SERVICE.view(postId);
                                STEP_2_VIEW.printPost(postId, postDto);
                            }
                            default -> STEP_2_VIEW.showInvalidInput();
                        }
                    }
                    case "accounts" -> {
                        switch (request.getUrl().getFunction()) {
                            case "signup" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.VISITOR)) {
                                    continue;
                                }
                                step.two_three.domain.AccountDto accountDto = STEP_2_VIEW.inputAccountDto();
                                STEP_2_ACCOUNT_SERVICE.signup(accountDto);
                            }
                            case "signin" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.VISITOR)) {
                                    continue;
                                }
                                step.two_three.domain.LoginForm loginForm = STEP_2_VIEW.inputLoginForm();
                                request.setSession(STEP_2_ACCOUNT_SERVICE.signIn(request.getSession(), loginForm));
                                STEP_2_VIEW.login(request.getSession());
                            }
                            case "signout" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                request.setSession(STEP_2_VIEW.signOut(request.getSession()));
                            }
                            case "detail" -> {
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                Optional<step.two_three.domain.AccountDto> accountDto = STEP_2_ACCOUNT_SERVICE.detail(accountId);
                                STEP_2_VIEW.detail(accountId, accountDto);
                            }
                            case "edit" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                boolean existResult = STEP_2_ACCOUNT_SERVICE.exist(accountId);
                                if (!STEP_2_VIEW.checkAccount(accountId, existResult)) {
                                    continue;
                                }
                                step.two_three.domain.AccountDto updateAccount = STEP_2_VIEW.inputUpdateAccount();
                                boolean editResult = STEP_2_ACCOUNT_SERVICE.edit(accountId, updateAccount);
                                STEP_2_VIEW.updateAccount(accountId, editResult);
                            }
                            case "remove" -> {
                                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                                    continue;
                                }
                                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get("accountId"));
                                boolean removeResult = STEP_2_ACCOUNT_SERVICE.remove(accountId);
                                STEP_2_VIEW.deleteAccount(accountId, removeResult);
                            }
                            default -> STEP_2_VIEW.showInvalidInput();
                        }
                    }
                    default -> STEP_2_VIEW.showInvalidInput();
                }
            } catch (IncorrectInputException | IOException e) {
                STEP_2_VIEW.showInvalidInput();
            }
        }
    }
}
