package cl.healthmood.Health.Mood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter @Setter
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String description;

}
