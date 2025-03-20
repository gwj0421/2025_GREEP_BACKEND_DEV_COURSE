package step_2_3.domain;

import java.time.LocalDateTime;

public class Board extends BaseEntity{
    private final Long boardId;
    private String name;
    private Long accountId;

    public Board(Long boardId, String name, Long accountId) {
        this.boardId = boardId;
        this.name = name;
        this.accountId = accountId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getName() {
        return name;
    }

    public void update(String name) {
        this.name = name;
        setUpdatedAt(LocalDateTime.now());
    }
}
