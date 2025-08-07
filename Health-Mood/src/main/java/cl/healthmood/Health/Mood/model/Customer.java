    package cl.healthmood.Health.Mood.model;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.Builder;

    import jakarta.persistence.*;
    import java.time.LocalDate;
    import java.util.List;

    @Entity
    @Table(name = "customers")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "customer_id")
        private Integer customerId;

        @Column(name = "first_name", nullable = false, length = 50)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 50)
        private String lastName;

        @Column(name = "phone", length = 20)
        private String phone;

        @Column(name = "email", length = 100, unique = true)
        private String email;

        @Column(name = "street", length = 100)
        private String street;

        @Column(name = "city", length = 50)
        private String city;

        @Column(name = "commune", length = 50)
        private String commune;

        @Column(name = "passwords", length = 65)
        private String password;

        @Column(name = "register_date")
        private LocalDate registerDate;

        @Column(name = "customerscol", length = 45)
        private String customersCol;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Order> orders;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Payment> payments;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Comment> comments;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<ChatbotLog> chatbotLogs;
    }
