package step.one.repository;

import step.one.domain.Post;
import step.one.domain.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PostRepository {
    private final List<Post> repository;
    private Long primaryKey;

    public PostRepository() {
        this.repository = new ArrayList<>();
        this.primaryKey = 0L;
    }

    public Post save(PostDto postDto) {
        Post saved = new Post(primaryKey++, postDto.getTitle(), postDto.getContent());
        repository.add(saved);
        return saved;
    }

    public List<Post> findAll() {
        return repository;
    }

    public Optional<Post> findById(Long postId) {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getId().equals(postId)) {
                return Optional.of(repository.get(i));
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(Long postId) {
        Optional<Post> findPost = findById(postId);
        if (findPost.isPresent()) {
            return repository.remove(findPost.get());
        }
        return false;
    }

    public boolean updateById(Long postId, PostDto postDto) {
        Optional<Post> findPost = findById(postId);
        if (findPost.isPresent()) {
            findPost.get().update(postDto);
            return true;
        }
        return false;
    }

    public boolean existById(Long postId) {
        return findById(postId).isPresent();
    }


}
