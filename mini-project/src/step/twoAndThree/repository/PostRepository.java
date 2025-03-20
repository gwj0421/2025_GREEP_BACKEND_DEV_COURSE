package step_2_3.repository;

import step_2_3.domain.Post;
import step_2_3.domain.PostDto;

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

    public void create(Long boardId, PostDto postDto,Long accountId) {
        repository.add(new Post(primaryKey++, postDto.getTitle(), postDto.getContent(), boardId, accountId));
    }

    public Optional<PostDto> read(Long postId) {
        for (Post post : repository) {
            if (post.getPostId() == postId) {
                return Optional.of(new PostDto(post.getTitle(), post.getContent(), post.getCreatedAt()));
            }
        }
        return Optional.empty();
    }

    public boolean update(Long postId, PostDto postDto) {
        for (Post post : repository) {
            if (post.getPostId() == postId) {
                post.update(postDto);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long postId) {
        for (Post post : repository) {
            if (post.getPostId() == postId) {
                repository.remove(post);
                return true;
            }
        }
        return false;
    }

    public boolean exist(Long postId) {
        for (Post post : repository) {
            if (post.getPostId() == postId) {
                return true;
            }
        }
        return false;
    }

    public List<PostDto> findByBoardId(Long boardId) {
        List<PostDto> posts = new ArrayList<>();
        for (Post post : repository) {
            if (post.getBoardId() == boardId) {
                posts.add(new PostDto(post.getTitle(), post.getContent(), post.getCreatedAt()));
            }
        }
        return posts;
    }
}
