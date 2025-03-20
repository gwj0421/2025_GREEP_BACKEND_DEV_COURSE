package step.two_three.controller;

import base.exception.IncorrectInputException;
import base.utils.StringUtils;
import base.utils.UrlUtils;
import step.two_three.domain.PostDto;
import step.two_three.domain.Request;
import step.two_three.domain.Role;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static base.config.Config.*;
import static base.utils.UrlUtils.*;

public class Step2Controller {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Request request = new Request();

        while (true) {
            try {
                String originUrl = STEP_2_VIEW.inputCommand();
                request.setUrl(UrlUtils.makeUrl(originUrl));
                switch (request.getUrl().getCategory()) {
                    case BOARD -> processBoard(request);
                    case POST -> processPost(request);
                    case ACCOUNT -> processAccount(request);
                    default -> STEP_2_VIEW.showInvalidInput();
                }
            } catch (IncorrectInputException | IOException e) {
                STEP_2_VIEW.showInvalidInput();
            }
        }
    }

    private static void processBoard(Request request) throws IOException {
        switch (request.getUrl().getFunction()) {
            case ADD -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                    return;
                }
                String boardName = STEP_2_VIEW.create();
                STEP_2_BOARD_SERVICE.create(boardName, request.getSession().getAccountId());
            }
            case EDIT -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                    return;
                }
                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get(BOARD_ID_KEY));
                boolean existResult = STEP_2_BOARD_SERVICE.exist(boardId);
                if (!STEP_2_VIEW.checkBoard(boardId, existResult)) {
                    return;
                }
                String boardName = STEP_2_VIEW.create();
                boolean result = STEP_2_BOARD_SERVICE.update(boardId, boardName);
                STEP_2_VIEW.checkUpdateBoard(boardId, result);
            }
            case REMOVE -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.ADMIN)) {
                    return;
                }
                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get(BOARD_ID_KEY));
                boolean result = STEP_2_BOARD_SERVICE.delete(boardId);
                STEP_2_VIEW.deleteBoard(boardId, result);
            }
            case VIEW -> {
                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get(BOARD_ID_KEY));
                List<PostDto> postsInBoard = STEP_2_BOARD_SERVICE.findAllByBoardId(boardId);
                STEP_2_VIEW.printPostsInBoard(postsInBoard);
            }
            default -> STEP_2_VIEW.showInvalidInput();
        }
    }

    private static void processPost(Request request) throws IOException {
        switch (request.getUrl().getFunction()) {
            case ADD -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                Long boardId = StringUtils.parseLong(request.getUrl().getParameters().get(BOARD_ID_KEY));
                boolean existResult = STEP_2_BOARD_SERVICE.exist(boardId);
                if (!STEP_2_VIEW.checkBoard(boardId, existResult)) {
                    return;
                }
                PostDto postDto = STEP_2_VIEW.inputPostDto(boardId);
                STEP_2_POST_SERVICE.add(boardId, postDto, request.getSession().getAccountId());
            }
            case REMOVE -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get(POST_ID_KEY));
                boolean result = STEP_2_POST_SERVICE.remove(postId);
                STEP_2_VIEW.deletePost(postId, result);
            }
            case EDIT -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get(POST_ID_KEY));
                boolean existResult = STEP_2_POST_SERVICE.exist(postId);
                if (!STEP_2_VIEW.checkPost(postId, existResult)) {
                    return;
                }
                PostDto postDto = STEP_2_VIEW.updatePostDto();
                STEP_2_POST_SERVICE.edit(postId, postDto);
            }
            case VIEW -> {
                Long postId = StringUtils.parseLong(request.getUrl().getParameters().get("postId"));
                Optional<PostDto> postDto = STEP_2_POST_SERVICE.view(postId);
                STEP_2_VIEW.printPost(postId, postDto);
            }
            default -> STEP_2_VIEW.showInvalidInput();
        }
    }

    private static void processAccount(Request request) throws IOException {
        switch (request.getUrl().getFunction()) {
            case SIGN_UP -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.VISITOR)) {
                    return;
                }
                step.two_three.domain.AccountDto accountDto = STEP_2_VIEW.inputAccountDto();
                STEP_2_ACCOUNT_SERVICE.signup(accountDto);
            }
            case SIGN_IN -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.VISITOR)) {
                    return;
                }
                step.two_three.domain.LoginForm loginForm = STEP_2_VIEW.inputLoginForm();
                request.setSession(STEP_2_ACCOUNT_SERVICE.signIn(request.getSession(), loginForm));
                STEP_2_VIEW.login(request.getSession());
            }
            case SIGN_OUT -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                request.setSession(STEP_2_VIEW.signOut(request.getSession()));
            }
            case DETAIL -> {
                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get(ACCOUNT_ID_KEY));
                Optional<step.two_three.domain.AccountDto> accountDto = STEP_2_ACCOUNT_SERVICE.detail(accountId);
                STEP_2_VIEW.detail(accountId, accountDto);
            }
            case EDIT -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get(ACCOUNT_ID_KEY));
                boolean existResult = STEP_2_ACCOUNT_SERVICE.exist(accountId);
                if (!STEP_2_VIEW.checkAccount(accountId, existResult)) {
                    return;
                }
                step.two_three.domain.AccountDto updateAccount = STEP_2_VIEW.inputUpdateAccount();
                boolean editResult = STEP_2_ACCOUNT_SERVICE.edit(accountId, updateAccount);
                STEP_2_VIEW.updateAccount(accountId, editResult);
            }
            case REMOVE -> {
                if (!STEP_2_ACCOUNT_SERVICE.authenticate(request, Role.USER)) {
                    return;
                }
                Long accountId = StringUtils.parseLong(request.getUrl().getParameters().get(ACCOUNT_ID_KEY));
                boolean removeResult = STEP_2_ACCOUNT_SERVICE.remove(accountId);
                STEP_2_VIEW.deleteAccount(accountId, removeResult);
            }
            default -> STEP_2_VIEW.showInvalidInput();
        }
    }
}
