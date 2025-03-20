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
    public static Step1View step1View = new Step1View();
    public static PostRepository step1PostRepository = new PostRepository();
    public static PostService step1PostService = new PostServiceImpl(step1PostRepository);

    public static Step2View step2View = new Step2View();
    public static BoardRepository step2BoardRepository = new BoardRepository();
    public static step2And3.repository.PostRepository step2PostRepository = new step2And3.repository.PostRepository();
    public static BoardService step2BoardService = new BoardServiceImpl(step2BoardRepository, step2PostRepository);
    public static step2And3.service.PostService step2PostService = new step2And3.service.PostServiceImpl(step2PostRepository);
    public static AccountRepository step2AccountRepository = new AccountRepository();
    public static AccountService step2AccountService = new AccountServiceImpl(step2AccountRepository);
}
