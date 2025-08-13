package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.dto.*;
import cl.healthmood.Health.Mood.model.Customer;
import cl.healthmood.Health.Mood.repository.CustomerRepository;
import cl.healthmood.Health.Mood.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                customer.getEmail(), 
                customer.getRol(), 
                customer.getCustomerId()
        );

        return LoginResponse.builder()
                .token(token)
                .customerId(customer.getCustomerId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .role(customer.getRol())
                .build();
    }

    public CustomerResponse register(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .street(request.getStreet())
                .city(request.getCity())
                .commune(request.getCommune())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol() != null ? request.getRol() : "USER")
                .registerDate(LocalDate.now())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.builder()
                .customerId(savedCustomer.getCustomerId())
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .phone(savedCustomer.getPhone())
                .email(savedCustomer.getEmail())
                .street(savedCustomer.getStreet())
                .city(savedCustomer.getCity())
                .commune(savedCustomer.getCommune())
                .registerDate(savedCustomer.getRegisterDate())
                .rol(savedCustomer.getRol())
                .build();
    }

    public LoginResponse refreshToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token format");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Token expired");
        }

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtUtil.generateToken(
                customer.getEmail(), 
                customer.getRol(), 
                customer.getCustomerId()
        );

        return LoginResponse.builder()
                .token(newToken)
                .customerId(customer.getCustomerId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .role(customer.getRol())
                .build();
    }
}