package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Long id(Long id);
    // JPARepository nos da el CRUD completo automaticamente
}
