package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long>{


}
