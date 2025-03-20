package base.config;

import step1.repository.PostRepository;
import step1.service.PostService;
import step1.service.PostServiceImpl;
import step1.view.Step1View;
import step2And3.repository.AccountRepository;
import step2And3.repository.BoardRepository;
import step2And3.service.AccountService;
import step2And3.service.AccountServiceImpl;
import step2And3.service.BoardService;
import step2And3.service.BoardServiceImpl;
import step2And3.view.Step2View;

public class Config {
    private Config() {
    }

    public static final Step1View STEP_1_VIEW = new Step1View();
    public static final PostRepository STEP_1_POST_REPOSITORY = new PostRepository();
    public static final PostService STEP_1_POST_SERVICE = new PostServiceImpl(STEP_1_POST_REPOSITORY);

    public static final Step2View STEP_2_VIEW = new Step2View();
    public static final BoardRepository STEP_2_BOARD_REPOSITORY = new BoardRepository();
    public static final step2And3.repository.PostRepository STEP_2_POST_REPOSITORY = new step2And3.repository.PostRepository();
    public static final BoardService STEP_2_BOARD_SERVICE = new BoardServiceImpl(STEP_2_BOARD_REPOSITORY, STEP_2_POST_REPOSITORY);
    public static final step2And3.service.PostService STEP_2_POST_SERVICE = new step2And3.service.PostServiceImpl(STEP_2_POST_REPOSITORY);
    public static final AccountRepository STEP_2_ACCOUNT_REPOSITORY = new AccountRepository();
    public static final AccountService STEP_2_ACCOUNT_SERVICE = new AccountServiceImpl(STEP_2_ACCOUNT_REPOSITORY);
}
