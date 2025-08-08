package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Customer;
import cl.healthmood.Health.Mood.repository.CustomerRepository;
import cl.healthmood.Health.Mood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        // Validaciones antes de guardar
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        // Verificar email único
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + customer.getEmail());
        }

        // Limpiar espacios en blanco
        customer.setFirstName(customer.getFirstName().trim());
        customer.setLastName(customer.getLastName().trim());
        customer.setEmail(customer.getEmail().trim());

        if (customer.getPhone() != null) {
            customer.setPhone(customer.getPhone().trim());
        }
        if (customer.getStreet() != null) {
            customer.setStreet(customer.getStreet().trim());
        }
        if (customer.getCity() != null) {
            customer.setCity(customer.getCity().trim());
        }
        if (customer.getCommune() != null) {
            customer.setCommune(customer.getCommune().trim());
        }
        if (customer.getRol() != null) {
            customer.setRol(customer.getRol().trim());
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setPhone(customer.getPhone());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setStreet(customer.getStreet());
                    existingCustomer.setCity(customer.getCity());
                    existingCustomer.setCommune(customer.getCommune());
                    existingCustomer.setPassword(customer.getPassword());
                    existingCustomer.setRol(customer.getRol());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByCity(String city) {
        return customerRepository.findByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByCommune(String commune) {
        return customerRepository.findByCommune(commune);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByFullName(String name) {
        return customerRepository.findByFullNameContaining(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }
}
