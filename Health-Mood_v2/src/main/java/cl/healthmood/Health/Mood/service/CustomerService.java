package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    Customer save(Customer customer);

    Customer update(Integer id, Customer customer);

    void deleteById(Integer id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByCity(String city);

    List<Customer> findByCommune(String commune);

    List<Customer> findByFullName(String name);

    boolean existsByEmail(String email);

    boolean existsById(Integer id);
}