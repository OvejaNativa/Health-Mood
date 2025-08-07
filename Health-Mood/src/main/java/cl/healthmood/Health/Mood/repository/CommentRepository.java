package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}