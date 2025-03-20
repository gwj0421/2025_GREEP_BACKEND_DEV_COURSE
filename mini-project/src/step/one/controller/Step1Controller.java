package step.one.controller;


import base.exception.IncorrectInputException;
import step.one.domain.PostDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static base.config.Config.STEP_1_POST_SERVICE;
import static base.config.Config.STEP_1_VIEW;

public class Step1Controller {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        while (true) {
            try {
                String command = STEP_1_VIEW.inputCommand();
                switch (command) {
                    case "안녕하세요!" -> STEP_1_VIEW.greeting();
                    case "종료" -> {
                        STEP_1_VIEW.exit();
                        return;
                    }
                    case "작성" -> {
                        PostDto postDto = STEP_1_VIEW.create();
                        STEP_1_POST_SERVICE.create(postDto);
                    }
                    case "조회" -> {
                        Long postId = STEP_1_VIEW.beforeRead();
                        Optional<PostDto> findPost = STEP_1_POST_SERVICE.read(postId);
                        STEP_1_VIEW.afterRead(postId, findPost);
                    }
                    case "삭제" -> {
                        Long postId = STEP_1_VIEW.beforeDelete();
                        boolean deleteResult = STEP_1_POST_SERVICE.delete(postId);
                        STEP_1_VIEW.afterDelete(postId, deleteResult);
                    }
                    case "수정" -> {
                        Long postId = STEP_1_VIEW.beforeUpdate();
                        Optional<PostDto> findPost = STEP_1_POST_SERVICE.read(postId);
                        processUpdatePost(postId, findPost);
                    }
                    case "목록" -> {
                        List<PostDto> posts = STEP_1_POST_SERVICE.findAll();
                        STEP_1_VIEW.showAllPosts(posts);
                    }
                    default -> STEP_1_VIEW.showInvalidInput();
                }
            } catch (IncorrectInputException | IOException e) {
                STEP_1_VIEW.showInvalidInput();
            }
        }
    }

    private static void processUpdatePost(Long postId, Optional<PostDto> findPost) throws IOException {
        if (!STEP_1_VIEW.checkUpdate(postId, findPost)) {
            return;
        }
        PostDto updatePost = STEP_1_VIEW.inputUpdatedPost(postId);
        if (!STEP_1_POST_SERVICE.update(postId, updatePost)) {
            return;
        }
        STEP_1_VIEW.afterUpdate(postId);
    }
}
