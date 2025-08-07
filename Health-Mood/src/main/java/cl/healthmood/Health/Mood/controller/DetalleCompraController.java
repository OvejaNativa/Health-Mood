package com.bootcamp.controller;

import com.bootcamp.model.DetalleCompra;
import com.bootcamp.service.DetalleCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
@RequiredArgsConstructor
public class DetalleCompraController {

    private final DetalleCompraService detalleCompraService;

    @GetMapping
    public List<DetalleCompra> listar() {
        return detalleCompraService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> obtenerPorId(@PathVariable Long id) {
        return detalleCompraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleCompra> crear(@Valid @RequestBody DetalleCompra detalle) {
        return ResponseEntity.ok(detalleCompraService.guardar(detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
