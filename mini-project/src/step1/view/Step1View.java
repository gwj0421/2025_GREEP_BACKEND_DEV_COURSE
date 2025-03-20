package step1.view;

import base.message.CustomMessage;
import base.utils.StringUtils;
import step1.domain.PostDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class Step1View {
    private static final String PREFIX_COMMAND = "명령어 > ";
    private static final String SUFFIX_NUMBER = "번";
    private BufferedReader br;

    public Step1View() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Step1View(BufferedReader br) {
        this.br = br;
    }

    public String inputCommand() throws IOException {
        return StringUtils.trimPrefix(br.readLine(), PREFIX_COMMAND);
    }

    public void greeting() {
        CustomMessage.GREETING.printMessage();
    }

    public void exit() {
        CustomMessage.EXIT.printMessage();
    }

    public PostDto create() throws IOException {
        CustomMessage.INPUT_TITLE.printMessage(false);
        String title = br.readLine();
        CustomMessage.INPUT_CONTENT.printMessage(false);
        String content = StringUtils.toContent(br);
        return new PostDto(title, content);
    }

    public Long beforeRead() throws IOException {
        CustomMessage.QUEST_READ_POST.printMessage(true);
        return StringUtils.parseLong(br.readLine());
    }

    public void afterRead(Long postId, Optional<PostDto> postDto) {
        postDto.ifPresentOrElse(
                it -> CustomMessage.FORMAT_BRIEF_POST.printMessage(it.getTitle(), it.getContent()),
                () -> CustomMessage.FAIL_POST.printMessage(postId)
        );
    }

    public Long beforeDelete() throws IOException {
        CustomMessage.QUEST_DELETE_POST.printMessage(false);
        return StringUtils.parseLong(StringUtils.trimSuffix(br.readLine(), SUFFIX_NUMBER));
    }

    public void afterDelete(Long postId,boolean deleteResult) {
        if (deleteResult) {
            CustomMessage.SUCCESS_DELETE_POST.printMessage(postId);
            return;
        }
        CustomMessage.FAIL_POST.printMessage(postId);
    }

    public Long beforeUpdate() throws IOException {
        CustomMessage.QUEST_UPDATE_POST.printMessage(true);
        return StringUtils.parseLong(StringUtils.trimSuffix(br.readLine(), SUFFIX_NUMBER));
    }

    public boolean checkUpdate(Long postId,Optional<PostDto> findPost){
        if (findPost.isPresent()) {
            return true;
        }
        CustomMessage.FAIL_POST.printMessage(postId);
        return false;
    }

    public PostDto inputUpdatedPost(Long postId) throws IOException {
        CustomMessage.PREVIEW_UPDATE_POST.printMessage(postId);
        CustomMessage.INPUT_TITLE.printMessage(false);
        String title = br.readLine();
        CustomMessage.INPUT_CONTENT.printMessage(false);
        String content = StringUtils.toContent(br);
        return new PostDto(title, content);
    }

    public void afterUpdate(Long postId) {
        CustomMessage.SUCCESS_DELETE_POST.printMessage(postId);
    }

    public void showAllPosts(List<PostDto> posts) {
        CustomMessage.PREVIEW_TOTAL_POST_CNT.printMessage(posts.size());
        for (int i = posts.size() - 1; i > -1; i--) {
            CustomMessage.FORMAT_GENERAL_POST.printMessage(i + 1, posts.get(i).getTitle(), posts.get(i).getContent());
        }
    }
    public void showInvalidInput() {
        CustomMessage.NOT_UNDERSTAND.printMessage(true);
    }

}
