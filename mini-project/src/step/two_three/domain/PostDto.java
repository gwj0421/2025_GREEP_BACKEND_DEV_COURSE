package step.two_three.domain;

import java.time.LocalDateTime;

public class PostDto {
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public PostDto(String title, String content, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public PostDto(String title, String content, LocalDateTime createAt) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }

    public PostDto(String title, String content, Long boardId) {
        this.title = title;
        this.content = content;
        this.boardId = boardId;
    }

    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
