package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}