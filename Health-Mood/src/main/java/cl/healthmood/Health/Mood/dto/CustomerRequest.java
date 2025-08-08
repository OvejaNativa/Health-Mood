
package cl.healthmood.Health.Mood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Size(max = 50, message = "Commune must not exceed 50 characters")
    private String commune;

    @Size(min = 6, max = 200, message = "Password must be between 6 and 200 characters")
    private String password;

    @Size(max = 45, message = "Role must not exceed 45 characters")
    private String rol;
}