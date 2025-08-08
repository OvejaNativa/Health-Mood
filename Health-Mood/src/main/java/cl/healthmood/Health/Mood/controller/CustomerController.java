package cl.healthmood.Health.Mood.controller;

import cl.healthmood.Health.Mood.model.Customer;
import cl.healthmood.Health.Mood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            // Validación básica
            if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Verificar si el email ya existe
            if (customerService.existsByEmail(customer.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            Customer savedCustomer = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.update(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.findByEmail(email)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/firstname/{firstName}")
    public ResponseEntity<List<Customer>> getCustomersByFirstName(@PathVariable String firstName) {
        List<Customer> customers = customerService.findByFirstName(firstName);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/lastname/{lastName}")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String lastName) {
        List<Customer> customers = customerService.findByLastName(lastName);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable String city) {
        List<Customer> customers = customerService.findByCity(city);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/commune/{commune}")
    public ResponseEntity<List<Customer>> getCustomersByCommune(@PathVariable String commune) {
        List<Customer> customers = customerService.findByCommune(commune);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/fullname/{name}")
    public ResponseEntity<List<Customer>> getCustomersByFullName(@PathVariable String name) {
        List<Customer> customers = customerService.findByFullName(name);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = customerService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkCustomerExists(@PathVariable Integer id) {
        boolean exists = customerService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}