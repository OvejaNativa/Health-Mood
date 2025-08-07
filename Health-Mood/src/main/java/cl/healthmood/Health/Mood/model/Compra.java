package cl.healthmood.Health.Mood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto total es obligatorio")
    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDateTime fecha;

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // 🐾 Compra puede tener varios productos
    // Lo ideal sería usar una entidad intermedia llamada "DetalleCompra"
    // que conecte Compra con Producto

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> detalles;
}