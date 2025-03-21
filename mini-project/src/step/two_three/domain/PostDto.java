package step.two_three.domain;

import java.time.LocalDateTime;

public class PostDto {
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    private PostDto(String title, String content, Long boardId, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public static class PostDtoBuilder implements Builder<PostDto> {
        private String title;
        private String content;
        private Long boardId;
        private LocalDateTime createAt;
        private LocalDateTime updatedAt;

        private PostDtoBuilder() {
        }

        public static PostDtoBuilder builder() {
            return new PostDtoBuilder();
        }

        public PostDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostDtoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostDtoBuilder boardId(Long boardId) {
            this.boardId = boardId;
            return this;
        }

        public PostDtoBuilder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public PostDtoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @Override
        public PostDto build() {
            return null;
        }
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
