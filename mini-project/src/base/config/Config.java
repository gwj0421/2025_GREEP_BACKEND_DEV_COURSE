package base.config;

import step.one.repository.PostRepository;
import step.one.service.PostService;
import step.one.service.PostServiceImpl;
import step.one.view.Step1View;

public class Config {
    private Config() {
    }

    public static final Step1View STEP_1_VIEW = new Step1View();
    public static final PostRepository STEP_1_POST_REPOSITORY = new PostRepository();
    public static final PostService STEP_1_POST_SERVICE = new PostServiceImpl(STEP_1_POST_REPOSITORY);

    public static final step.two_three.view.Step2View STEP_2_VIEW = new step.two_three.view.Step2View();
    public static final step.two_three.repository.BoardRepository STEP_2_BOARD_REPOSITORY = new step.two_three.repository.BoardRepository();
    public static final step.two_three.repository.PostRepository STEP_2_POST_REPOSITORY = new step.two_three.repository.PostRepository();
    public static final step.two_three.service.BoardService STEP_2_BOARD_SERVICE = new step.two_three.service.BoardServiceImpl(STEP_2_BOARD_REPOSITORY, STEP_2_POST_REPOSITORY);
    public static final step.two_three.service.PostService STEP_2_POST_SERVICE = new step.two_three.service.PostServiceImpl(STEP_2_POST_REPOSITORY);
    public static final step.two_three.repository.AccountRepository STEP_2_ACCOUNT_REPOSITORY = new step.two_three.repository.AccountRepository();
    public static final step.two_three.service.AccountService STEP_2_ACCOUNT_SERVICE = new step.two_three.service.AccountServiceImpl(STEP_2_ACCOUNT_REPOSITORY);
}
