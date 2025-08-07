package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);

}