package step1.controller;


import base.exception.IncorrectInputException;
import step1.domain.PostDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static base.config.Config.step1PostService;
import static base.config.Config.step1View;

public class Step1Controller {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        while (true) {
            try {
                String command = step1View.inputCommand();
                switch (command) {
                    case "안녕하세요!" -> step1View.greeting();
                    case "종료" -> {
                        step1View.exit();
                        return;
                    }
                    case "작성" -> {
                        PostDto postDto = step1View.create();
                        step1PostService.create(postDto);
                    }
                    case "조회" -> {
                        Long postId = step1View.beforeRead();
                        Optional<PostDto> findPost = step1PostService.read(postId);
                        step1View.afterRead(postId, findPost);
                    }
                    case "삭제" -> {
                        Long postId = step1View.beforeDelete();
                        boolean deleteResult = step1PostService.delete(postId);
                        step1View.afterDelete(postId, deleteResult);
                    }
                    case "수정" -> {
                        Long postId = step1View.beforeUpdate();
                        Optional<PostDto> findPost = step1PostService.read(postId);
                        if (!step1View.checkUpdate(postId, findPost)) {
                            continue;
                        }
                        PostDto updatePost = step1View.inputUpdatedPost(postId);
                        if (!step1PostService.update(postId, updatePost)) {
                            continue;
                        }
                        step1View.afterUpdate(postId);
                    }
                    case "목록" -> {
                        List<PostDto> posts = step1PostService.findAll();
                        step1View.showAllPosts(posts);
                    }
                    default -> {
                        step1View.showInvalidInput();
                    }
                }
            } catch (IncorrectInputException | IOException e) {
                step1View.showInvalidInput();
            }
        }
    }
}
