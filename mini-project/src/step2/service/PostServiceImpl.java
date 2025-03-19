package step2.service;

import step2.domain.PostDto;
import step2.repository.PostRepository;

import java.util.Optional;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void add(Long boardId, PostDto postDto, Long accountId) {
        postRepository.create(boardId, postDto, accountId);
    }

    @Override
    public boolean edit(Long postId, PostDto postDto) {
        return postRepository.update(postId, postDto);
    }

    @Override
    public boolean remove(Long postId) {
        return postRepository.delete(postId);
    }

    @Override
    public Optional<PostDto> view(Long postId) {
        return postRepository.read(postId);
    }

    @Override
    public boolean exist(Long postId) {
        return postRepository.exist(postId);
    }
}
