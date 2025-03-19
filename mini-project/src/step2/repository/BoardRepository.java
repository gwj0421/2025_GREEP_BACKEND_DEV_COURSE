package step2.repository;

import step2.domain.Board;
import step2.domain.BoardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardRepository {
    private List<Board> repository;
    private Long primaryKey;

    public BoardRepository() {
        this.repository = new ArrayList<>();
        this.primaryKey = 0L;
    }

    public void create(String boardName, Long accountId) {
        repository.add(new Board(primaryKey++, boardName, accountId));
    }

    public Optional<BoardDto> read(Long boardId) {
        for (Board board : repository) {
            if (board.getBoardId() == boardId) {
                return Optional.of(new BoardDto(board.getName()));
            }
        }
        return Optional.empty();
    }

    public boolean update(Long boardId, String boardName) {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getBoardId() == boardId) {
                repository.get(i).update(boardName);
            }
            return true;
        }
        return false;
    }

    public boolean delete(Long boardId) {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getBoardId() == boardId) {
                repository.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean exist(Long boardId) {
        for (Board board : repository) {
            if (board.getBoardId() == boardId) {
                return true;
            }
        }
        return false;
    }

}
