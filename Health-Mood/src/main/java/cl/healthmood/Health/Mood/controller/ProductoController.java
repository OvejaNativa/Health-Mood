package cl.healthmood.Health.Mood.controller;
import cl.healthmood.Health.Mood.model.Producto;
import cl.healthmood.Health.Mood.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public List<Producto> lista(){
        return productoService.listarProductos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id){
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.guardar(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        productoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
