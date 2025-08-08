package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Integer id);
    Post savePost(Post post);
    void deletePost(Integer id);
}