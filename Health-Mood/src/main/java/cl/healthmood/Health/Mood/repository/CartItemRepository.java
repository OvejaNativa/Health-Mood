package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}