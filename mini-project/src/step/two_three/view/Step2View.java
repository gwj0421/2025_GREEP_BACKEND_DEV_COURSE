package step.two_three.view;

import base.message.CustomMessage;
import base.utils.StringUtils;
import step.two_three.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class Step2View {
    private static final String PREFIX_COMMAND = "a ";
    private BufferedReader br;

    public Step2View() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Step2View(BufferedReader br) {
        this.br = br;
    }

    public String inputCommand() throws IOException {
        System.out.print(PREFIX_COMMAND);
        return br.readLine();
    }

    public String create() throws IOException {
        CustomMessage.INPUT_TITLE.printMessage(false);
        return br.readLine();
    }

    public boolean checkBoard(Long boardId, boolean existResult) {
        if (existResult) {
            CustomMessage.PREVIEW_UPDATE_BOARD.printMessage(boardId);
            return true;
        }
        CustomMessage.FAIL_BOARD.printMessage(boardId);
        return false;
    }

    public void checkUpdateBoard(Long boardId, boolean result) {
        if (result) {
            CustomMessage.SUCCESS_UPDATE_BOARD.printMessage(boardId);
            return;
        }
        CustomMessage.FAIL_BOARD.printMessage(boardId);
    }

    public void deleteBoard(Long boardId, boolean result) {
        if (result) {
            CustomMessage.SUCCESS_DELETE_BOARD.printMessage(boardId);
            return;
        }
        CustomMessage.FAIL_BOARD.printMessage(boardId);
    }

    public void printPostsInBoard(List<PostDto> postsInBoard) {
        CustomMessage.TABLE_HEAD_ROW_IN_POST.printMessage(true);
        for (PostDto post : postsInBoard) {
            CustomMessage.TABLE_DATA_ROW_IN_POST.printMessage(post.getTitle(), post.getContent(), StringUtils.toDate(post.getCreateAt()));
        }
    }

    public boolean checkPost(Long postId, boolean existResult) {
        if (existResult) {
            CustomMessage.PREVIEW_UPDATE_POST.printMessage(postId);
            return true;
        }
        CustomMessage.FAIL_POST.printMessage(postId);
        return false;
    }

    public PostDto inputPostDto(Long boardId) throws IOException {
        CustomMessage.INPUT_TITLE.printMessage(false);
        String title = br.readLine();
        CustomMessage.INPUT_CONTENT.printMessage(false);
        String content = StringUtils.toContent(br);
        return new PostDto(title, content, boardId);
    }

    public void deletePost(Long postId, boolean result) {
        if (result) {
            CustomMessage.SUCCESS_DELETE_POST.printMessage(postId);
            return;
        }
        CustomMessage.FAIL_POST.printMessage(postId);
    }

    public PostDto updatePostDto() throws IOException {
        CustomMessage.INPUT_TITLE.printMessage(false);
        String title = br.readLine();
        CustomMessage.INPUT_CONTENT.printMessage(false);
        String content = StringUtils.toContent(br);
        return new PostDto(title, content);
    }

    public void printPost(Long postId, Optional<PostDto> postDto) {
        postDto.ifPresentOrElse(
                it -> CustomMessage.FORMAT_FULL_POST.printMessage(postId, StringUtils.toDate(it.getCreateAt()), StringUtils.toDate(it.getUpdatedAt()), it.getTitle(), it.getContent()),
                () -> CustomMessage.FAIL_POST.printMessage(postId)
        );
    }

    public AccountDto inputAccountDto() throws IOException {
        CustomMessage.INPUT_ID.printMessage(false);
        String id = br.readLine();
        CustomMessage.INPUT_PW.printMessage(false);
        String pw = br.readLine();
        CustomMessage.INPUT_NAME.printMessage(false);
        String name = br.readLine();
        CustomMessage.INPUT_NICKNAME.printMessage(false);
        String nickname = br.readLine();
        CustomMessage.INPUT_EMAIL.printMessage(false);
        String email = br.readLine();
        return new AccountDto(id, pw, name, nickname, email);
    }

    public LoginForm inputLoginForm() throws IOException {
        CustomMessage.INPUT_ID.printMessage(false);
        String id = br.readLine();
        CustomMessage.INPUT_PW.printMessage(false);
        String pw = br.readLine();
        return new LoginForm(id, pw);
    }

    public void login(Session session) {
        CustomMessage.FORMAT_ACCOUNT.printMessage(session.getName(), session.getRole().getDesc());
    }

    public Session signOut(Session session) {
        if (session.getRole() == Role.VISITOR) {
            CustomMessage.FAIL_LOGOUT.printMessage(true);
            return session;
        }
        CustomMessage.SUCCESS_LOGOUT.printMessage(true);
        return new Session();
    }

    public void detail(Long accountId, Optional<AccountDto> accountDto) {
        accountDto.ifPresentOrElse(
                it -> CustomMessage.FORMAT_FULL_ACCOUNT.printMessage(accountId, it.getId(), it.getEmail(), StringUtils.toDate(it.getCreateAt())),
                () -> CustomMessage.FAIL_ACCOUNT.printMessage(true)
        );
    }

    public boolean checkAccount(Long accountId, boolean existResult) {
        if (existResult) {
            CustomMessage.PREVIEW_UPDATE_ACCOUNT.printMessage(accountId);
            return true;
        }
        CustomMessage.FAIL_ACCOUNT.printMessage(true);
        return false;
    }

    public AccountDto inputUpdateAccount() throws IOException {
        CustomMessage.INPUT_PW.printMessage(false);
        String pw = br.readLine();
        CustomMessage.INPUT_EMAIL.printMessage(false);
        String email = br.readLine();
        return new AccountDto(pw, email);
    }

    public void updateAccount(Long accountId, boolean editResult) {
        if (editResult) {
            CustomMessage.SUCCESS_UPDATE_ACCOUNT.printMessage(accountId);
            return;
        }
        CustomMessage.FAIL_ACCOUNT.printMessage(true);
    }

    public void deleteAccount(Long accountId, boolean deleteResult) {
        if (deleteResult) {
            CustomMessage.SUCCESS_DELETE_ACCOUNT.printMessage(accountId);
            return;
        }
        CustomMessage.FAIL_ACCOUNT.printMessage(true);
    }

    public void showInvalidInput() {
        CustomMessage.NOT_UNDERSTAND.printMessage(true);
    }

}
