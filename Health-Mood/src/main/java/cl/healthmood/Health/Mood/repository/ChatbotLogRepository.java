package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.ChatbotLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotLogRepository extends JpaRepository<ChatbotLog, Integer> {

}