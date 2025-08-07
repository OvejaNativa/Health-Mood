
package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}