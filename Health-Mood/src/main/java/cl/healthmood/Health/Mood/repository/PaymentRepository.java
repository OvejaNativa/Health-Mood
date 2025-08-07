package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}