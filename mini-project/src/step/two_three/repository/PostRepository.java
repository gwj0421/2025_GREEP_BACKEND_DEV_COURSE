package step.two_three.repository;

import step.two_three.domain.Post;
import step.two_three.domain.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository {
    private List<Post> repository;
    private Long primaryKey;

    public PostRepository() {
        this.repository = new ArrayList<>();
        this.primaryKey = 0L;
    }

    public void create(Long boardId, PostDto postDto, Long accountId) {
        repository.add(new Post(primaryKey++, postDto.getTitle(), postDto.getContent(), boardId, accountId));
    }

    public Optional<PostDto> read(Long postId) {
        for (Post post : repository) {
            if (post.getPostId().equals(postId)) {
                return Optional.of(
                        PostDto.PostDtoBuilder.builder()
                                .title(post.getTitle())
                                .content(post.getContent())
                                .createAt(post.getCreatedAt())
                                .build()
                );
            }
        }
        return Optional.empty();
    }

    public boolean update(Long postId, PostDto postDto) {
        for (Post post : repository) {
            if (post.getPostId().equals(postId)) {
                post.update(postDto);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long postId) {
        for (Post post : repository) {
            if (post.getPostId().equals(postId)) {
                repository.remove(post);
                return true;
            }
        }
        return false;
    }

    public boolean exist(Long postId) {
        for (Post post : repository) {
            if (post.getPostId().equals(postId)) {
                return true;
            }
        }
        return false;
    }

    public List<PostDto> findByBoardId(Long boardId) {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : repository) {
            if (post.getBoardId().equals(boardId)) {
                posts.add(PostDto.PostDtoBuilder.builder().title(post.getTitle()).content(post.getContent()).createAt(post.getCreatedAt()).build());
            }
        }
        return posts;
    }
}
