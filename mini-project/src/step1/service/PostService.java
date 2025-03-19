package step1.service;

import step1.domain.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void create(PostDto postDto);

    Optional<PostDto> read(Long postId);

    boolean update(Long postId, PostDto postDto);

    boolean delete(Long postId);

    List<PostDto> findAll();
}
