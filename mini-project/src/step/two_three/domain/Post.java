package step.two_three.domain;

import java.time.LocalDateTime;

public class Post extends BaseEntity{
    private final Long postId;
    private String title;
    private String content;
    private Long boardId;
    private Long accountId;

    public Post(Long postId, String title, String content, Long boardId, Long accountId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.accountId = accountId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void update(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        setUpdatedAt(LocalDateTime.now());
    }
}
