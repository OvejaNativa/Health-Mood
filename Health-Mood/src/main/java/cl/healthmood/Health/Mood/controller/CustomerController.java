package cl.healthmood.Health.Mood.controller;

import cl.healthmood.Health.Mood.dto.CustomerRequest;
import cl.healthmood.Health.Mood.dto.CustomerResponse;
import cl.healthmood.Health.Mood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        try {
            CustomerResponse savedCustomer = customerService.save(customerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Email already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Email already exists: " + customerRequest.getEmail());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating customer");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerRequest customerRequest) {
        try {
            CustomerResponse updatedCustomer = customerService.update(id, customerRequest);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Customer not found")) {
                return ResponseEntity.notFound().build();
            } else if (e.getMessage().contains("Email already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Email already exists: " + customerRequest.getEmail());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating customer");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Customer not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) {
        return customerService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/firstname/{firstName}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByFirstName(@PathVariable String firstName) {
        List<CustomerResponse> customers = customerService.findByFirstName(firstName);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/lastname/{lastName}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByLastName(@PathVariable String lastName) {
        List<CustomerResponse> customers = customerService.findByLastName(lastName);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByCity(@PathVariable String city) {
        List<CustomerResponse> customers = customerService.findByCity(city);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/commune/{commune}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByCommune(@PathVariable String commune) {
        List<CustomerResponse> customers = customerService.findByCommune(commune);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search/fullname/{name}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByFullName(@PathVariable String name) {
        List<CustomerResponse> customers = customerService.findByFullName(name);
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