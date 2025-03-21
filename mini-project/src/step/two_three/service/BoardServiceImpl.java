package step.two_three.service;

import step.two_three.domain.BoardDto;
import step.two_three.domain.PostDto;
import step.two_three.repository.BoardRepository;
import step.two_three.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;
    private PostRepository postRepository;

    public BoardServiceImpl(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void create(String boardName, Long accountId) {
        boardRepository.create(boardName, accountId);
    }

    @Override
    public boolean update(Long boardId, String boardName) {
        return boardRepository.update(boardId, boardName);
    }

    @Override
    public boolean delete(Long boardId) {
        return boardRepository.delete(boardId);
    }

    @Override
    public List<PostDto> findAllByBoardId(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    @Override
    public Optional<BoardDto> read(Long boardId) {
        return boardRepository.read(boardId);
    }

    @Override
    public boolean exist(Long boardId) {
        return boardRepository.exist(boardId);
    }
}
