package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Integer> {

}