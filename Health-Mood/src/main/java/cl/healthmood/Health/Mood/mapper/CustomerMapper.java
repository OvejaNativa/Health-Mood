package cl.healthmood.Health.Mood.mapper;

import cl.healthmood.Health.Mood.dto.CustomerRequest;
import cl.healthmood.Health.Mood.dto.CustomerResponse;
import cl.healthmood.Health.Mood.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest customerRequest) {
        if (customerRequest == null) {
            return null;
        }

        return Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .phone(customerRequest.getPhone())
                .email(customerRequest.getEmail())
                .street(customerRequest.getStreet())
                .city(customerRequest.getCity())
                .commune(customerRequest.getCommune())
                .password(customerRequest.getPassword())
                .rol(customerRequest.getRol())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .street(customer.getStreet())
                .city(customer.getCity())
                .commune(customer.getCommune())
                .registerDate(customer.getRegisterDate())
                .rol(customer.getRol())
                .build();
    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        if (customers == null) {
            return null;
        }

        return customers.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntityFromRequest(CustomerRequest customerRequest, Customer customer) {
        if (customerRequest == null || customer == null) {
            return;
        }

        if (customerRequest.getFirstName() != null) {
            customer.setFirstName(customerRequest.getFirstName());
        }
        if (customerRequest.getLastName() != null) {
            customer.setLastName(customerRequest.getLastName());
        }
        if (customerRequest.getPhone() != null) {
            customer.setPhone(customerRequest.getPhone());
        }
        if (customerRequest.getEmail() != null) {
            customer.setEmail(customerRequest.getEmail());
        }
        if (customerRequest.getStreet() != null) {
            customer.setStreet(customerRequest.getStreet());
        }
        if (customerRequest.getCity() != null) {
            customer.setCity(customerRequest.getCity());
        }
        if (customerRequest.getCommune() != null) {
            customer.setCommune(customerRequest.getCommune());
        }
        if (customerRequest.getPassword() != null) {
            customer.setPassword(customerRequest.getPassword());
        }
        if (customerRequest.getRol() != null) {
            customer.setRol(customerRequest.getRol());
        }
    }
}