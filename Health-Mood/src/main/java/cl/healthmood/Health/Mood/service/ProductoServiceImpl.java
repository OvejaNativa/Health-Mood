package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Producto;
import cl.healthmood.Health.Mood.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    @Override
    public List<Producto> listarProductos(){
        return productoRepository.findAll();

    }
    @Override
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    @Override
    public Producto actualizar(Long id, Producto producto){
       producto.setId(id);
       return productoRepository.save(producto);
    }
    @Override
    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }
    @Override
    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }

}
