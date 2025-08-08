
package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    List<Customer> findByCity(String city);

    List<Customer> findByCommune(String commune);

    List<Customer> findByRegisterDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %:name% OR c.lastName LIKE %:name%")
    List<Customer> searchByName(@Param("name") String name);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(o) FROM Pedido o WHERE o.customer.customerId = :customerId")
    Long countOrdersByCustomerId(@Param("customerId") Integer customerId);

}