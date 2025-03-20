package base.config;

import step.one.repository.PostRepository;
import step.one.service.PostService;
import step.one.service.PostServiceImpl;
import step.one.view.Step1View;
import step.twoAndThree.repository.AccountRepository;
import step.twoAndThree.repository.BoardRepository;
import step.twoAndThree.service.AccountService;
import step.twoAndThree.service.AccountServiceImpl;
import step.twoAndThree.service.BoardService;
import step.twoAndThree.service.BoardServiceImpl;
import step.twoAndThree.view.Step2View;

public class Config {
    private Config() {
    }

    public static final Step1View STEP_1_VIEW = new Step1View();
    public static final PostRepository STEP_1_POST_REPOSITORY = new PostRepository();
    public static final PostService STEP_1_POST_SERVICE = new PostServiceImpl(STEP_1_POST_REPOSITORY);

    public static final Step2View STEP_2_VIEW = new Step2View();
    public static final BoardRepository STEP_2_BOARD_REPOSITORY = new BoardRepository();
    public static final step_2_3.repository.PostRepository STEP_2_POST_REPOSITORY = new step_2_3.repository.PostRepository();
    public static final BoardService STEP_2_BOARD_SERVICE = new BoardServiceImpl(STEP_2_BOARD_REPOSITORY, STEP_2_POST_REPOSITORY);
    public static final step_2_3.service.PostService STEP_2_POST_SERVICE = new step_2_3.service.PostServiceImpl(STEP_2_POST_REPOSITORY);
    public static final AccountRepository STEP_2_ACCOUNT_REPOSITORY = new AccountRepository();
    public static final AccountService STEP_2_ACCOUNT_SERVICE = new AccountServiceImpl(STEP_2_ACCOUNT_REPOSITORY);
}
