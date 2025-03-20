package step.one.service;

import step.one.domain.Post;
import step.one.domain.PostDto;
import step.one.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void create(PostDto postDto) {
        postRepository.save(postDto);
    }

    @Override
    public Optional<PostDto> read(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new PostDto(findPost.get().getTitle(), findPost.get().getContent()));
    }

    @Override
    public boolean update(Long postId, PostDto postDto) {
        Optional<Post> findPost = postRepository.findById(postId);
        findPost.ifPresent(it -> it.update(postDto));
        return findPost.isPresent();
    }

    @Override
    public boolean delete(Long postId) {
        return postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            posts.add(new PostDto(post.getTitle(), post.getContent()));
        }
        return posts;
    }

}
