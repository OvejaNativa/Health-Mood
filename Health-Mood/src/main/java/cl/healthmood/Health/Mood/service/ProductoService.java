package cl.healthmood.Health.Mood.service;
import cl.healthmood.Health.Mood.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarProductos();
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    Optional<Producto> obtenerPorId(Long id);
    void eliminar(Long id);
}
