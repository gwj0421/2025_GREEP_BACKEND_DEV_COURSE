package step_2_3.service;

import step_2_3.domain.PostDto;

import java.util.Optional;

public interface PostService {
    void add(Long boardId, PostDto postDto,Long accountId);

    boolean edit(Long postId,PostDto postDto);

    boolean remove(Long postId);

    Optional<PostDto> view(Long postId);

    boolean exist(Long postId);
}
