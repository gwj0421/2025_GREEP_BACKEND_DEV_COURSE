package step_2_3.service;

import step_2_3.domain.BoardDto;
import step_2_3.domain.PostDto;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    void create(String boardName,Long accountId);

    Optional<BoardDto> read(Long boardId);

    boolean exist(Long boardId);

    boolean update(Long boardId, String boardName);

    boolean delete(Long boardId);

    List<PostDto> findAllByBoardId(Long boardId);
}
