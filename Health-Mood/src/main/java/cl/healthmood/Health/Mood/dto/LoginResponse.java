package cl.healthmood.Health.Mood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
    private String type = "Bearer";
    private Integer customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}